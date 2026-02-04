package dev.morafa.todo_app.service;

import dev.morafa.todo_app.model.Todo;
import dev.morafa.todo_app.model.User;
import dev.morafa.todo_app.repository.TodoRepository;
import dev.morafa.todo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private UserRepository userRepository;

  // Create a new task using the DTO
  public Todo saveTodo(Long userId, Todo todo) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new RuntimeException("User not found"));
    todo.setUser(user);
    return todoRepository.save(todo);
  }
  
  // Fetch all tasks
  public List<Todo> getTodosByUser(Long userId) {
    return todoRepository.findByUserIdOrderByIdAsc(userId);
  }
  
  // Update an existing task
  public Todo updateTodo(Long id, Todo todoDetails) {
    Todo todo = todoRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Todo not found"));
    todo.setTitle(todoDetails.getTitle()); 
    return todoRepository.save(todo);
  }

  // Delete a task
  public void deleteTodo(Long id) {
    if (!todoRepository.existsById(id)) {
      throw new RuntimeException("Cannot delete: Todo not found with ID: " + id);
    }
    todoRepository.deleteById(id);
  }
}