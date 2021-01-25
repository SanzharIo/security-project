package kz.project.demo.controllers;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUsers")
    public ResponseEntity<List<AuthorizedUser>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/getUser")
    public ResponseEntity<AuthorizedUser> getOneUser(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }

//    @PostMapping("/addUser")
//    public ResponseEntity<AuthorizedUser> addUser(@RequestBody AuthorizedUser) {
//        return ResponseEntity.ok().body(userService.getOneById(id));
//    }

    @PostMapping("/deleteUser")
    public ResponseEntity<AuthorizedUser> deleteUserById(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }
}
