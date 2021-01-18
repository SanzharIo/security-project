package kz.project.demo.repositories;

import kz.project.demo.model.entities.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<AuthorizedUser, Long> {

    List<AuthorizedUser> getAllByDeletedAtIsNull();

    AuthorizedUser getUsersById(Long id);

    AuthorizedUser getAllByPhoneAndDeletedAtIsNull(String phone);


}
