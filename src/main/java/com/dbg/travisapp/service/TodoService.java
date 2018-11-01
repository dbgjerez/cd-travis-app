package com.dbg.travisapp.service;

import com.dbg.travisapp.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface TodoService {

    Optional<Todo> findById(Integer idTodo);

    Todo create(Todo todo);

    void update(Integer idTodo,
                Todo dto);

    void delete(Integer idTodo);

    Page<Todo> findAll(PageRequest pageable,
                       Optional<Boolean> done);
}
