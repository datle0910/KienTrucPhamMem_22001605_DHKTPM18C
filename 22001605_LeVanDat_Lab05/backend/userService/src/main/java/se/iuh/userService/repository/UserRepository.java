package se.iuh.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.iuh.userService.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
