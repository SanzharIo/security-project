package kz.project.demo.repositories;

import kz.project.demo.model.entities.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<AuthorizedUser, Long> {

    List<AuthorizedUser> getAllByDeletedAtIsNull();

    AuthorizedUser getUsersById(Long id);

    AuthorizedUser getAllByPhoneAndDeletedAtIsNull(String phone);

    @Query(value = "SELECT u.phone FROM AuthorizedUser u WHERE u.phone=?1")
    String getValidationKeyByPhone(String phone);


}
