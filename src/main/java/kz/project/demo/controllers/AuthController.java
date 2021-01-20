package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.model.entities.Role;
import kz.project.demo.model.errors.ErrorCode;
import kz.project.demo.model.errors.ServiceException;
import kz.project.demo.services.roles.RoleService;
import kz.project.demo.services.users.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<HttpStatus> signUp(@RequestBody AuthorizedUser authorizedUser) {
        authorizedUser.setPassword(bCryptPasswordEncoder.encode(authorizedUser.getPassword()));

        if (userService.getUserByPhone(authorizedUser.getPhone()) != null) {
            throw ServiceException.builder().message("Такой телефон уже существует").errorCode(ErrorCode.ALREADY_EXISTS).httpStatus(HttpStatus.IM_USED).build();
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOneById(2L));
        String generatedString = RandomStringUtils.random(6, false, true);
        authorizedUser.setValidationKey(generatedString);
        authorizedUser.setRoles(roles);
        userService.save(authorizedUser);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
