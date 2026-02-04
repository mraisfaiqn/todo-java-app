package dev.morafa.todo_app.service;

import dev.morafa.todo_app.model.Todo;
import dev.morafa.todo_app.dto.TodoDTO;
import dev.morafa.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
  @Autowired
  private TodoRepository todoRepository;

  // Fetch all tasks
  public List<Todo> getAllTodos() {
    return todoRepository.findAllByOrderByIdAsc();
  }
  
  // Create a new task using the DTO
  public Todo createTodo(TodoDTO todoDTO) {
    if (todoDTO.getTitle() == null || todoDTO.getTitle().isEmpty()) {
      throw new RuntimeException("Title cannot be empty!");
    }
    Todo todo = new Todo();
    todo.setTitle(todoDTO.getTitle());
    return todoRepository.save(todo);
  }

  // Update an existing task
  public Todo updateTodo(Long id, TodoDTO todoDTO) {
    Todo todo = todoRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
    
    todo.setTitle(todoDTO.getTitle());
    return todoRepository.save(todo);
  }

  // Delete a task
  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }
}