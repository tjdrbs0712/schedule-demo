package com.sparta.scheduleproject2.dto;

import com.sparta.scheduleproject2.repository.Todo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoResponseDTO {
    private Long todoId;
    private String title;
    private String content;
    private String username;
    private LocalDate createdAt;

    public TodoResponseDTO(Todo todo) {
        this.todoId = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.username = todo.getUsername();
        this.createdAt = LocalDate.now();
    }
}
