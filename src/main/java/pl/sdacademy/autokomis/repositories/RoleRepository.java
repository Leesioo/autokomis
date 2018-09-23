package pl.sdacademy.autokomis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.autokomis.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
