package dev.morafa.todo_app.controller;

import dev.morafa.todo_app.model.Todo;
import dev.morafa.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // Crucial for letting your frontend talk to this backend
public class TodoController {

  @Autowired
  private TodoService todoService;

  //Create a new task and link it to a user.
  //POST http://localhost:8080/api/todos/{userId}
  @PostMapping("/{userId}")
  public Todo saveTodo(@PathVariable Long userId, @RequestBody Todo todo) {
    return todoService.saveTodo(userId, todo);
  }

  //Fetch all tasks belonging to a specific user.
  //GET http://localhost:8080/api/todos/{userId}
  @GetMapping("/{userId}")
  public List<Todo> getTodosByUser(@PathVariable Long userId) {
    return todoService.getTodosByUser(userId);
  }

  //Edit an existing task's title.
  //PUT http://localhost:8080/api/todos/{id}
  @PutMapping("/{id}")
  public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    try {
      Todo updatedTodo = todoService.updateTodo(id, todo);
      return ResponseEntity.ok(updatedTodo);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // Delete a task from the database.
  // DELETE http://localhost:8080/api/todos/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
    try {
      todoService.deleteTodo(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}