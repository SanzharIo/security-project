package kz.project.demo.services.roles;

import kz.project.demo.model.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getOneById(Long id);

    void save(Role role);

    void deleteById(Long id);

}
