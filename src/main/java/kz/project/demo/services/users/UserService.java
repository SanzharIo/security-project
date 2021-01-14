package kz.project.demo.services.users;

import kz.project.demo.model.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getOneById(Long id);

    User getUserByEmail(String email);

    User getUserByPhone(String phone);

    User save(User user);
}
