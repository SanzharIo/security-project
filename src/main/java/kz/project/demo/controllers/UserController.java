package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<AuthorizedUser>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/getOneUser")
    public ResponseEntity<AuthorizedUser> getOneUser(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }

    @GetMapping("/profile")
    public ResponseEntity<AuthorizedUser> userProfile(@RequestParam Long id) {
        AuthorizedUser authorizedUser = userService.getOneById(id);
        return ResponseEntity.ok().body(authorizedUser);
    }


}
