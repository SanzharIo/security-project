package kz.project.demo.repositories;

import kz.project.demo.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> getAllByDeletedAtIsNull();

    User getUsersById(Long id);

    User getUsersByEmailAndDeletedAtNotNull(String email);

    User getAllByPhoneAndDeletedAtNotNull(String phone);

    User getByPhone(String phone);

}
