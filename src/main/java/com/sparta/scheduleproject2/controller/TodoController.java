package com.sparta.scheduleproject2.controller;


import com.sparta.scheduleproject2.CommonResponse;
import com.sparta.scheduleproject2.dto.TodoRequestDTO;
import com.sparta.scheduleproject2.dto.TodoResponseDTO;
import com.sparta.scheduleproject2.repository.Todo;
import com.sparta.scheduleproject2.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/todo")
public class TodoController {

    public final TodoService todoService;

    // TODO 일정 작성 기능
    @PostMapping
    public ResponseEntity<CommonResponse> createTodo(@RequestBody TodoRequestDTO todoRequestDTO){

        Todo todo = todoService.createTodo(todoRequestDTO);
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료되었습니다.\n")
                        .data(todoResponseDTO)
                        .build());
    }

    // TODO id로 일정 조회
    @GetMapping("{todoId}")
    public ResponseEntity<CommonResponse> getTodoId(@PathVariable Long todoId){
        Todo todo = todoService.getTodoId(todoId);
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.\n")
                .data(todoResponseDTO)
                .build());
    }

    // TODO 일정 전체 조회
    @GetMapping()
    public ResponseEntity<CommonResponse> getTodo(){
        List<Todo> todoList = todoService.getTodo();
        List<TodoResponseDTO> todoResponseDTO = todoList.stream().map(TodoResponseDTO::new).toList();
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("조회가 완료되었습니다.\n")
                .data(todoResponseDTO)
                .build());
    }

    // TODO 일정 수정
    @PutMapping("{todoId}")
    public ResponseEntity<CommonResponse> updateTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO todoRequestDTO){
        Todo todo = todoService.updateTodo(todoId, todoRequestDTO);
        TodoResponseDTO todoResponseDTO = new TodoResponseDTO(todo);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("수정이 완료되었습니다.\n")
                .data(todoResponseDTO)
                .build());
    }

    // TODO 일정 삭제
    @DeleteMapping("{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO todoRequestDTO){
        todoService.deleteTodo(todoId, todoRequestDTO.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("삭제가 완료되었습니다.\n")
                .build());
    }
}
