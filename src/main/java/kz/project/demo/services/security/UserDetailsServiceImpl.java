package kz.project.demo.services.security;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.errors.ErrorCode;
import kz.project.demo.model.errors.ServiceException;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        AuthorizedUser authorizedUser = checkUser(phone);
        if (authorizedUser == null) {
            throw ServiceException.builder().message("Такой телефон не существует").httpStatus(HttpStatus.NOT_FOUND).errorCode(ErrorCode.AUTH_ERROR).build();
        }
        return new org.springframework.security.core.userdetails.User(authorizedUser.getPhone(), authorizedUser.getPassword(), authorizedUser.getRoles());
    }

    public AuthorizedUser checkUser(String phone) {
        return userService.getByPhone(phone);
    }

}