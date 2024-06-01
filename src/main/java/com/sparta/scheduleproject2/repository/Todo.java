package com.sparta.scheduleproject2.repository;

import com.sparta.scheduleproject2.dto.TodoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "create_at", nullable = false)
    private LocalDate createdAt;

    @Builder
    public Todo(String title, String content, String username, String password) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDate.now();
    }

    public void update(TodoRequestDTO todoRequestDTO){
        this.title = todoRequestDTO.getTitle();
        this.content = todoRequestDTO.getContent();
        this.username = todoRequestDTO.getUsername();
        this.createdAt = LocalDate.now();
    }
}
