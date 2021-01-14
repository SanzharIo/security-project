package kz.project.demo.services.users;

import kz.project.demo.model.entities.User;
import kz.project.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return repository.getAllByDeletedAtIsNull();
    }

    @Override
    public User getOneById(Long id) {
        return repository.getUsersById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.getUsersByEmailAndDeletedAtNotNull(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return repository.getAllByPhoneAndDeletedAtNotNull(phone);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
