package kz.project.demo.services.users.v1;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.requests.RegistrationRequest;
import kz.project.demo.paginator.PageResponse;

import java.util.Optional;

public interface UserService {

    PageResponse getAll(Optional<String> search,
                             Optional<Integer> page,
                             Optional<Integer> size,
                             Optional<String[]> sortBy);

    Iterable<AuthorizedUser> getOne(Optional<String> search);

    AuthorizedUser getOneById(Long id);

    AuthorizedUser getByPhone(String phone);

    void save(RegistrationRequest registrationRequest);

    void deleteByPhone(String phone);

    void activate(String phone, String key);

    void changePassword(String phone, String password,String newPassword);
}
