package kz.project.demo.controllers.users;

import kz.project.demo.model.entities.AuthorizedUser;
import kz.project.demo.paginator.PageResponse;
import kz.project.demo.services.users.v1.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<PageResponse> getAll(Optional<String> search,
                                               Optional<Integer> page,
                                               Optional<Integer> size,
                                               Optional<String[]> sortBy) {
        return ResponseEntity.ok().body(userService.getAll(search,page,size,sortBy));
    }

    @GetMapping("/getOne")
    public ResponseEntity<Iterable<AuthorizedUser>> getOne(Optional<String> search){
        return ResponseEntity.ok().body(userService.getOne(search));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<HttpStatus> passwordChange(@RequestParam String phone, @RequestParam String password, @RequestParam String newPassword){
        userService.changePassword(phone,password,newPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getOneById")
    public ResponseEntity<AuthorizedUser> getOneById(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }

    @PostMapping("/deleteById")
    public ResponseEntity<AuthorizedUser> deleteById(@RequestParam Long id) {
        return ResponseEntity.ok().body(userService.getOneById(id));
    }
}
