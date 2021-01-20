package kz.project.demo.services.bot;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.TelegramUser;
import kz.project.demo.services.telegramUsers.TelegramUserService;
import kz.project.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
        if (update.hasMessage() && update.getMessage().hasText())
            switch (text) {
                case "/start":
                    TelegramUser tUser = telegramUserService.getOnByChatId(message.getChatId());
                    if (tUser == null) {
                        String userPhone = contact.getPhoneNumber();
                        TelegramUser telegramUser = TelegramUser.builder()
                                .phone(userPhone)
                                .chatId(message.getChatId())
                                .name(contact.getFirstName())
                                .surname(contact.getLastName())
                                .build();
                        if (telegramUser.getPhone() != null) {
                            AuthorizedUser authorizedUser = userService.getUserByPhone(telegramUser.getPhone());
                            telegramUser.setAuthorizedUser(authorizedUser);
                        }
                        telegramUserService.save(telegramUser);
                    }
                    setAnswer(message.getChatId(), "Welcome to test market");
                    break;

                case "/activation-code":
                    if (contact.getPhoneNumber() == null) {
                        setAnswer(message.getChatId(), "Please disable your hiding number phone");
                    } else {
                        AuthorizedUser authorizedUser = userService.getUserByPhone(contact.getPhoneNumber());
                        setAnswer(message.getChatId(), "Your activation key " + authorizedUser.getValidationKey());
                    }
                    break;
            }
    }


    private void setAnswer(Long chatId, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
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
