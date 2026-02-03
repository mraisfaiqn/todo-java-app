package dev.morafa.todo_app.controller;

import dev.morafa.todo_app.model.Todo;
import dev.morafa.todo_app.dto.TodoDTO;
import dev.morafa.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos") //URL endpoint
@CrossOrigin // This allows your frontend to talk to this backend later
public class TodoController {

    @Autowired
    private TodoService todoService;

    // This method will handle "POST" requests to /api/todos
    @PostMapping
    public Todo createTodo(@RequestBody TodoDTO todoDTO) {
      return todoService.createTodo(todoDTO);
    }

    // This method will handle "GET" requests to /api/todos
    @GetMapping
    public List<Todo> getAllTodos() {
      return todoService.getAllTodos();
    }

    // This method will handle "PUT" requests to /api/todos/{id}
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
      return todoService.updateTodo(id, todoDTO);
    }
    
    // This method will handle "DELETE" requests to /api/todos/{id}
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
      todoService.deleteTodo(id);
    }
}