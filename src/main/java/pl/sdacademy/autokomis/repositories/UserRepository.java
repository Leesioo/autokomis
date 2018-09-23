package pl.sdacademy.autokomis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.autokomis.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
