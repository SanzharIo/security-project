package kz.project.demo.controllers.auth;

import kz.project.demo.model.requests.RegistrationRequest;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/auth/")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody RegistrationRequest registrationRequest) {
        userService.save(registrationRequest);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/active")
    public ResponseEntity<HttpStatus> accountActivation(@RequestParam String phone, @RequestParam String key) {
        userService.activate(phone,key);
        return ResponseEntity.ok().build();
    }
}
