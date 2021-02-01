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
@RequestMapping("/v1/sellers")
public class SellerController {

    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<AuthorizedUser> sellerProfile(@RequestParam String phone) {
        AuthorizedUser user = userService.getUserByPhone(phone);
        return ResponseEntity.ok().body(user);
    }

}
