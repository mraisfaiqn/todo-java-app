package dev.morafa.todo_app.service;

import dev.morafa.todo_app.model.User;
import dev.morafa.todo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  public User register(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
        throw new RuntimeException("User already exists");
    }
    return userRepository.save(user);
  }

  public Optional<User> login(String username, String password) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent() && user.get().getPassword().equals(password)) {
      return user;
    }
    return Optional.empty();
  }
}