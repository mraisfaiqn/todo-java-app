package dev.morafa.todo_app.repository;

import dev.morafa.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByUserId(Long userId);
}