package dev.morafa.todo_app.controller;

import dev.morafa.todo_app.model.User;
import dev.morafa.todo_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allows your frontend to talk to the backend
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      return ResponseEntity.badRequest().body("Error: Username already exists!");
    }
    userRepository.save(user);
    return ResponseEntity.ok("Registration successful!");
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody User user) {
    Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

    if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
      // Create a JSON response object
      Map<String, Object> response = new HashMap<>();
      response.put("message", "Login Successful");
      response.put("userId", foundUser.get().getId());
      
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(401).body("Invalid Credentials");
    }
  }
}