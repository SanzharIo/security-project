package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<AuthorizedUser> userProfile(@RequestParam String phone) {
        AuthorizedUser authorizedUser = userService.getUserByPhone(phone);
        return ResponseEntity.ok().body(authorizedUser);
    }

}
