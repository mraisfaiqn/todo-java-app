package dev.morafa.todo_app.repository;

import dev.morafa.todo_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find user by name
    Optional<User> findByUsername(String username);
}