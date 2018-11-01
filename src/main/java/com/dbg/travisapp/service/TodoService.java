package com.dbg.travisapp.service;

import com.dbg.travisapp.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TodoService {

    Page<Todo> findAll(Pageable pageable);

    Optional<Todo> findById(Integer idTodo);

    Todo create(Todo todo);

    void update(Integer idTodo, Todo dto);

    void delete(Integer idTodo);

}
