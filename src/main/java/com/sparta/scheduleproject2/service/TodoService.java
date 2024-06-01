package com.sparta.scheduleproject2.service;

import com.sparta.scheduleproject2.dto.TodoRequestDTO;
import com.sparta.scheduleproject2.repository.Todo;
import com.sparta.scheduleproject2.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // TODO 일정 작성 기능
    public Todo createTodo(TodoRequestDTO todoRequestDTO){

        Todo todo = todoRequestDTO.toEntity();
        return todoRepository.save(todo);

    }

    // TODO id로 일정 조회 기능
    public Todo getTodoId(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("해당 id의 일정이 없습니다."));
    }

    // TODO 일정 전체 조회
    public List<Todo> getTodo() {
        return todoRepository.findAllByOrderByIdDesc();
    }

    // TODO 일정 수정
    @Transactional
    public Todo updateTodo(Long todoId, TodoRequestDTO todoRequestDTO) {
        Todo todo = checkPassword(todoId, todoRequestDTO.getPassword());

        todo.update(todoRequestDTO);
        return todo;
    }


    // TODO 일정 삭제
    @Transactional
    public void deleteTodo(Long todoId, String password) {
        Todo todo = checkPassword(todoId, password);

        todoRepository.delete(todo);
    }

    //비밀번호 체크
    private Todo checkPassword(Long todoId, String password) {
        Todo todo = getTodoId(todoId);

        if(todo.getPassword() != null &&
                !todo.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return todo;
    }
}
