package kz.project.demo.controllers;

import kz.project.demo.model.entities.Role;
import kz.project.demo.model.entities.User;
import kz.project.demo.repositories.RoleRepository;
import kz.project.demo.repositories.UserRepository;
import kz.project.demo.services.roles.RoleService;
import kz.project.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        if (userService.getUserByPhone(user.getPhone()) != null) {
            return ResponseEntity.ok().body(HttpStatus.IM_USED);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOneById(2L));

        user.setRoles(roles);
        userService.save(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/getOne")
    public ResponseEntity<User> getOneUser(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }

}
