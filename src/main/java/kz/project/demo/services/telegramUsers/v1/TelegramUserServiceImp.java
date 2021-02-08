package kz.project.demo.services.telegramUsers.v1;


import kz.project.demo.model.entities.TelegramUser;
import kz.project.demo.repositories.TelegramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TelegramUserServiceImp implements TelegramUserService {

    @Autowired
    private TelegramUserRepository repository;

    @Override
    public List<TelegramUser> getAll() {
        return repository.getAllByDeletedAtIsNull();
    }

    @Override
    public TelegramUser getOneById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public TelegramUser getOnePhoneNumber(String phone) {
        return repository.getByPhoneAndDeletedAtIsNull(phone);
    }

    @Override
    public TelegramUser getOneByChatId(Long id) {
        return repository.getByChatIdAndDeletedAtIsNull(id);
    }

    @Override
    public void save(TelegramUser telegramUser) {
        repository.save(telegramUser);
    }

    @Override
    public void delete(Long id) {
        TelegramUser telegramUser = repository.getOne(id);
        telegramUser.setDeletedAt(new Date());
        repository.save(telegramUser);
    }
}
