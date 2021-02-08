package kz.project.demo.services.telegramUsers.v1;

import kz.project.demo.model.entities.TelegramUser;

import java.util.List;

public interface TelegramUserService {

    List<TelegramUser> getAll();

    TelegramUser getOneById(Long id);

    TelegramUser getOnePhoneNumber(String phone);

    TelegramUser getOneByChatId(Long id);

    void save(TelegramUser telegramUser);

    void delete(Long id);
}
