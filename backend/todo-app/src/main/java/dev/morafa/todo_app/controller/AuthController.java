package dev.morafa.todo_app.controller;

import dev.morafa.todo_app.model.User;
import dev.morafa.todo_app.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // This sets the base URL for these actions
public class AuthController {

  private final AuthService authService = new AuthService();

  @PostMapping("/register")
  public String registerUser(@RequestBody User user) {
    // This takes the JSON from the frontend and passes it to our logic
    return authService.register(user.getUsername(), user.getPassword());
  }

  @PostMapping("/login")
  public String loginUser(@RequestBody User user) {
    boolean success = authService.login(user.getUsername(), user.getPassword());
    return success ? "Login Successful!" : "Invalid Credentials";
  }
}