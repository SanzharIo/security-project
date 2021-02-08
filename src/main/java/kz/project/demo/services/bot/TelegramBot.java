package kz.project.demo.services.bot;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.TelegramUser;
import kz.project.demo.services.telegramUsers.v1.TelegramUserService;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBot extends TelegramLongPollingCommandBot {

    @Autowired
    private TelegramUserService telegramUserService;

    @Autowired
    private UserService userService;

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Contact contact = message.getContact();
        String text = message.getText();
        User user = message.getFrom();

        if (contact != null) {
            TelegramUser telegramUser = telegramUserService.getOneByChatId(message.getChatId());
            telegramUser.setPhone(contact.getPhoneNumber());
            AuthorizedUser authorizedUser = userService.getByPhone(telegramUser.getPhone());
            if (authorizedUser != null) {
                telegramUser.setAuthorizedUser(authorizedUser);
            } else {
                setAnswer(message.getChatId(), "Thank you");
                setAnswer(message.getChatId(), "Now press the button /activationKey");
            }
            telegramUserService.save(telegramUser);
        }

        if (update.hasMessage() && update.getMessage().hasText())
            switch (text) {
                case "/start":
                case "start":
                case "Start":
                    TelegramUser tUser = telegramUserService.getOneByChatId(message.getChatId());
                    if (tUser == null) {
                        TelegramUser telegramUser = TelegramUser.builder()
                                .chatId(message.getChatId())
                                .name(user.getFirstName())
                                .surname(user.getLastName())
                                .build();
                        telegramUserService.save(telegramUser);
                    }
                    setAnswer(message.getChatId(), "Welcome to test market");
                    break;

                case "/activationKey":
                case "Get activation key":
                    TelegramUser telegramUser = telegramUserService.getOneByChatId(message.getChatId());
                    if (telegramUser.getPhone() != null) {
                        AuthorizedUser authorizedUser = userService.getByPhone(("+" + telegramUser.getPhone()).trim());
                        if (authorizedUser != null) {
                            telegramUser.setAuthorizedUser(authorizedUser);
                            telegramUserService.save(telegramUser);
                            if (authorizedUser.getIsValid()) {
                                setAnswer(message.getChatId(), "You have already activated your account");
                            } else {
                                setAnswer(message.getChatId(), authorizedUser.getValidationKey());
                            }
                        } else {
                            setAnswer(message.getChatId(), "You have not registered your account");
                            setAnswer(message.getChatId(), "Please go to registration page in order to create your account");
                            setAnswer(message.getChatId(), "http://test.kz");
                        }
                    } else {
                        setAnswer(message.getChatId(), "Please press (Share your number >) in order to show your phone number" +
                                "\n then press the Get activation key button or type /activationKey");
                    }
                    break;
            }
    }


    private ReplyKeyboardMarkup setKeyBoard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // new list
        List<KeyboardRow> keyboard = new ArrayList<>();

        // first keyboard line
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        KeyboardButton kb1 = new KeyboardButton();
        kb1.setText("Get activation key");
        keyboardFirstRow.add(kb1);

        // second keyboard line
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        KeyboardButton kb2 = new KeyboardButton();
        kb2.setText("Share your number >");
        kb2.setRequestContact(true);
        keyboardSecondRow.add(kb2);


        // add array to list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        // add list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }


    private void setAnswer(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        answer.setReplyMarkup(setKeyBoard());

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Test Market";
    }

    @Override
    public String getBotToken() {
        return "1570685276:AAF-vQFbsVvprAgSdOtbMWe7Oe66Q78Hm4U";
    }
}
