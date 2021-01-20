package kz.project.demo.services.bot;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.services.telegramUsers.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBot extends TelegramLongPollingCommandBot {

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        update.getMessage().getContact().getPhoneNumber();


        message.getForwardFrom().getId();
    }

    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    private String checkUser(AuthorizedUser authorizedUser){
//    }

    @Override
    public String getBotUsername() {
        return "bot_name";
    }

    @Override
    public String getBotToken() {
        return "bot_token";
    }
}
