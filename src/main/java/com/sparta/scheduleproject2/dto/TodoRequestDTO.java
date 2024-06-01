package com.sparta.scheduleproject2.dto;

import com.sparta.scheduleproject2.repository.Todo;
import lombok.Getter;

@Getter
public class TodoRequestDTO {
    private String title;
    private String content;
    private String username;
    private String password;

    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .content(content)
                .username(username)
                .password(password)
                .build();
    }
}
