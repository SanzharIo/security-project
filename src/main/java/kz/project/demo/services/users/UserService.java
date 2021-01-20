package kz.project.demo.services.users;

import kz.project.demo.model.entities.AuthorizedUser;

import java.util.List;

public interface UserService {

    List<AuthorizedUser> getAllUsers();

    AuthorizedUser getOneById(Long id);

    String getValidationKeyByPhone(String phone);

    AuthorizedUser getUserByPhone(String phone);

    AuthorizedUser save(AuthorizedUser authorizedUser);
}
