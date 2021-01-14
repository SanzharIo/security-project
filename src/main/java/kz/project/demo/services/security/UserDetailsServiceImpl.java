package kz.project.demo.services.security;

import kz.project.demo.model.entities.User;
import kz.project.demo.model.errors.ErrorCode;
import kz.project.demo.model.errors.ServiceException;
import kz.project.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.getByPhone(phone);
        if (user == null) {
            throw ServiceException.builder().message("Такой телефон уже существует").httpStatus(HttpStatus.IM_USED).errorCode(ErrorCode.ALREADY_EXISTS).build();
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), user.getRoles());
    }

    public User checkUsername(String phone) {
        User user = userRepository.getByPhone(phone);
        if (user == null) {
            throw ServiceException.builder().message("Такого юзера не существует").errorCode(ErrorCode.AUTH_ERROR).httpStatus(HttpStatus.NOT_FOUND).build();
        }
        return user;
    }

}