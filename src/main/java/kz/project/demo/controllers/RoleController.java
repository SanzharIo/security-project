package kz.project.demo.controllers;

import kz.project.demo.model.entities.Role;
import kz.project.demo.repositories.RoleRepository;
import kz.project.demo.services.roles.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    public ResponseEntity<HttpStatus> addRole(@RequestParam String role){
        roleService.save(new Role(null,role));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/deleteRole")
    public ResponseEntity<HttpStatus> deleteRole(@RequestParam String role){
        roleService.save(new Role(null,role));
        return ResponseEntity.ok().build();
    }
}
