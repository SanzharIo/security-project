package kz.project.demo.services.users.v1;

import com.querydsl.core.types.dsl.BooleanExpression;
import kz.project.demo.methods.Pagination;
import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.Role;
import kz.project.demo.model.errors.ErrorCode;
import kz.project.demo.model.errors.ServiceException;
import kz.project.demo.model.requests.RegistrationRequest;
import kz.project.demo.paginator.PageResponse;
import kz.project.demo.predicates.UserPredicatesBuilder;
import kz.project.demo.repositories.RoleRepository;
import kz.project.demo.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServicesImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Pagination pagination;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public PageResponse getAll(Optional<String> search,
                                            Optional<Integer> page,
                                            Optional<Integer> size,
                                            Optional<String[]> sortBy) {
        UserPredicatesBuilder builder = new UserPredicatesBuilder();

        if (search.isPresent()) {
            Pattern pattern = Pattern.compile("(\\w+?[.]\\w+?|\\w+?)" +
                    "(:|<|>)" +
                    "(\\w+?\\s\\w+?|\\w+?|\\w+?\\s\\w+?\\s\\w+?|\\w+?[-]\\w+?[-]\\w+?\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(search.get() + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();

        Page<AuthorizedUser> users = userRepository.findAll(exp, pagination.paginate(page, size, sortBy));
        return commonAll(users);
    }

    @Override
    public Iterable<AuthorizedUser> getOne(Optional<String> search) {
        UserPredicatesBuilder builder = new UserPredicatesBuilder();

        if (search.isPresent()) {
            Pattern pattern = Pattern.compile("(\\w+?[.]\\w+?|\\w+?)" +
                    "(:|<|>)" +
                    "(\\w+?\\s\\w+?|\\w+?|\\w+?\\s\\w+?\\s\\w+?|\\w+?[-]\\w+?[-]\\w+?\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher = pattern.matcher(search.get() + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();

        return userRepository.findAll(exp);
    }

    @Override
    public AuthorizedUser getOneById(Long id) {
        return userRepository.getUsersById(id);
    }

    @Override
    public AuthorizedUser getByPhone(String phone) {
        return userRepository.getByPhoneAndDeletedAtIsNull(phone);
    }

    @Override
    public void save(RegistrationRequest registrationRequest) {
        if (userRepository.getByPhoneAndDeletedAtIsNull(registrationRequest.getPhone()) != null) {
            throw ServiceException.builder().message("Такой телефон уже существует")
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .httpStatus(HttpStatus.IM_USED).build();
        }
        AuthorizedUser authorizedUser = new AuthorizedUser();
        authorizedUser.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        String generatedString = RandomStringUtils.random(6, false, true);
        authorizedUser.setValidationKey(generatedString);

        Set<Role> roles = new HashSet<>();
        switch (registrationRequest.getUserType()) {
            case "user":
                roles.add(roleRepository.getOne(2L));
                break;
            case "seller":
                roles.add(roleRepository.getOne(3L));
                break;
            case "admin":
                roles.add(roleRepository.getOne(1L));
                break;
        }
        authorizedUser.setRoles(roles);
        userRepository.save(authorizedUser);
    }

    @Override
    public void deleteByPhone(String phone) {
        AuthorizedUser user = userRepository.getByPhoneAndDeletedAtIsNull(phone);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void activate(String phone, String key) {
        AuthorizedUser authorizedUser = userRepository.getByPhoneAndDeletedAtIsNull(phone);
        if (!authorizedUser.getIsValid()) {
            if (authorizedUser.getValidationKey().equals(key)) {
                authorizedUser.setIsValid(true);
                userRepository.save(authorizedUser);
            } else {
                throw ServiceException.builder().message("incorrect activation key")
                        .httpStatus(HttpStatus.FORBIDDEN)
                        .errorCode(ErrorCode.INVALID_AUTHENTICATION_KEY).build();
            }
        } else {
            throw ServiceException.builder().message("account was already activated")
                    .httpStatus(HttpStatus.IM_USED)
                    .errorCode(ErrorCode.INVALID_AUTHENTICATION_KEY).build();
        }
    }

    @Override
    public void changePassword(String phone, String password, String newPassword) {
        AuthorizedUser authorizedUser = userRepository.getByPhoneAndDeletedAtIsNull(phone);
        String newUserPassword = bCryptPasswordEncoder.encode(newPassword);
        if (authorizedUser.getPhone().equals(newUserPassword)) {
                authorizedUser.setPassword(newUserPassword);
        }else {
            throw ServiceException.builder().httpStatus(HttpStatus.FORBIDDEN).errorCode(ErrorCode.PASSWORDS_MISMATCH).message("Вы ввели неправельны пароль").build();
        }
    }

    private PageResponse commonAll(Page<AuthorizedUser> userPage) {
        return pagination.convertToPageResponse(userPage.getContent(), userPage);
    }
}
