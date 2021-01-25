package kz.project.demo.services.users.v1;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImp implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<AuthorizedUser> getAllUsers() {
        return repository.getAllByDeletedAtIsNull();
    }

    @Override
    public AuthorizedUser getOneById(Long id) {
        return repository.getUsersById(id);
    }

    @Override
    public AuthorizedUser getUserByPhone(String phone) {
        return repository.getAllByPhoneAndDeletedAtIsNull(phone);
    }

    @Override
    public AuthorizedUser save(AuthorizedUser authorizedUser) {
        return repository.save(authorizedUser);
    }
}
