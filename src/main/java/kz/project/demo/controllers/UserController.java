package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
