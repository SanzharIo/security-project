package kz.project.demo.controllers.users;

import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/changePassword")
    public ResponseEntity<HttpStatus> passwordChanging(@RequestParam String phone, @RequestParam String password, @RequestParam String newPassword){
        userService.changePassword(phone,password,newPassword);
        return ResponseEntity.ok().build();
    }

}
