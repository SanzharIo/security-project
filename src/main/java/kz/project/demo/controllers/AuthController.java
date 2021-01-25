package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.Role;
import kz.project.demo.model.errors.ErrorCode;
import kz.project.demo.model.errors.ServiceException;
import kz.project.demo.model.requests.RegistrationRequest;
import kz.project.demo.services.roles.RoleService;
import kz.project.demo.services.users.v1.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody RegistrationRequest registrationRequest) {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        authorizedUser.setPassword(registrationRequest.getPhone());

        authorizedUser.setPassword(bCryptPasswordEncoder.encode(authorizedUser.getPassword()));

        if (userService.getUserByPhone(authorizedUser.getPhone()) != null) {
            throw ServiceException.builder().message("Такой телефон уже существует")
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .httpStatus(HttpStatus.IM_USED).build();
        }

        Set<Role> roles = new HashSet<>();
        switch (registrationRequest.getUserType()) {
            case "user":
                roles.add(roleRepository.getOneById(2L));
                break;
            case "seller":
                roles.add(roleRepository.getOneById(3L));
                break;
            case "admin":
                roles.add(roleRepository.getOneById(1L));
                break;
        }
        String generatedString = RandomStringUtils.random(6, false, true);

        authorizedUser.setValidationKey(generatedString);
        authorizedUser.setIsValid(false);
        authorizedUser.setRoles(roles);

        userService.save(authorizedUser);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/active")
    public ResponseEntity<HttpStatus> accountActivation(@RequestParam String phone, @RequestParam String key) {

        AuthorizedUser authorizedUser = userService.getUserByPhone(phone);
        if (!authorizedUser.getIsValid()) {
            if (authorizedUser.getValidationKey().equals(key)) {
                authorizedUser.setIsValid(true);
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
        return ResponseEntity.ok().build();
    }
}
