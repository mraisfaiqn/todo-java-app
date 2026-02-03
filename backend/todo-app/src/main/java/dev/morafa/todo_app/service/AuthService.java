package dev.morafa.todo_app.service;

import java.util.ArrayList;
import java.util.List;

import dev.morafa.todo_app.model.User;

public class AuthService {
  // This list acts as our temporary database 📂
  private List<User> users = new ArrayList<>();

  // 1. Method to create a new account
  public String register(String newUsername, String newPassword) {
    // 1. Loop through the list to check for duplicates
    for (User existingUser : users) {
      if (existingUser.getUsername().equals(newUsername)) {
        return "Error: Username already exists!";
      }
    }

    // 2. If the loop finishes without finding a match, add the new user
    User newUser = new User(newUsername, newPassword);
    users.add(newUser);
    return "Registration successful!";
  }

  // 2. Method to verify existing users
  public boolean login(String username, String password) {
    // 1. We loop through our list to find the right person
    for (User user : users) {
      // 2. Check if the username matches
      if (user.getUsername().equals(username)) {
        // 3. Now we check if the password matches
        if (user.getPassword().equals(password)) {
          System.out.println("Login successful! Welcome, " + username);
          return true; // Access granted
        }
      }
    }
    
    // If the loop finishes and we didn't return true, the login failed
    System.out.println("Login failed: Invalid username or password.");
    return false; // Access denied
  }
}