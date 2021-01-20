package kz.project.demo.repositories;

import kz.project.demo.model.entities.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {

    List<TelegramUser> getAllByDeletedAtIsNull();

}
