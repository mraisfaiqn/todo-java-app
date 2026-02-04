package dev.morafa.todo_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items") // Maps to your 'items' table 
@Getter @Setter @NoArgsConstructor
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Creates the 'SERIAL' behavior
  private Long id;

  @Column(nullable = false, length = 100) // Creates 'VARCHAR(100) NOT NULL'
  private String title;
}