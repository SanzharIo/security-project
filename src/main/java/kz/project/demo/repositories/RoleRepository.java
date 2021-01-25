package kz.project.demo.repositories;

import kz.project.demo.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
