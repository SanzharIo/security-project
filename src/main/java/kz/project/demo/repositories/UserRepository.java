package kz.project.demo.repositories;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.QAuthorizedUser;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableAutoConfiguration(exclude = {AuthorizedUser.class})
@Repository
public interface UserRepository extends JpaRepository<AuthorizedUser, Long>, QuerydslPredicateExecutor<AuthorizedUser>, QuerydslBinderCustomizer<QAuthorizedUser> {

    List<AuthorizedUser> getAllByDeletedAtIsNull();

    AuthorizedUser getUsersById(Long id);

    AuthorizedUser getByPhoneAndDeletedAtIsNull(String phone);

    @Query(value = "SELECT u.phone FROM AuthorizedUser u WHERE u.phone=?1")
    String getValidationKeyByPhone(String phone);

    @Override
    default void customize(QuerydslBindings bindings, QAuthorizedUser root){
        bindings.bind(String.class)
                .first((SingleValueBinding< StringPath,String >) StringExpression::containsIgnoreCase);
        bindings.excluding(root._super);
    }
}
