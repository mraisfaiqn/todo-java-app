package dev.morafa.todo_app.controller;

import dev.morafa.todo_app.model.Todo;
import dev.morafa.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos") //URL endpoint
@CrossOrigin // This allows your frontend to talk to this backend later
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    // This method will handle "POST" requests to /api/todos
    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
      // This takes the 'todo' data sent in the request and saves it
      return todoRepository.save(todo);
    }

    // This method will handle "GET" requests to /api/todos
    @GetMapping
    public List<Todo> getAllTodos() {
      return todoRepository.findAll();
    }

    // This method will handle "PUT" requests to /api/todos/{id}
    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
      // 1. Find the existing task
      Todo todo = todoRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
      
      // 2. Update the title (and any other fields)
      todo.setTitle(todoDetails.getTitle());
      
      // 3. Save the changes back to the database
      return todoRepository.save(todo);
    }
    
    // This method will handle "DELETE" requests to /api/todos/{id}
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
      todoRepository.deleteById(id);
    }
}