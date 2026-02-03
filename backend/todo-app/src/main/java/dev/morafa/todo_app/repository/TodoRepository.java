package dev.morafa.todo_app.repository;

import dev.morafa.todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
  // This interface is empty for now, but it already has 
  // methods like .save(), .findAll(), and .deleteById()!
}