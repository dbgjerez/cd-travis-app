package com.dbg.travisapp.service;

import com.dbg.travisapp.dao.TodoDAO;
import com.dbg.travisapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO dao;

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Optional<Todo> findById(Integer idTodo) {
        return dao.findById(idTodo);
    }

    @Override
    public Todo create(Todo todo) {
        return dao.save(todo);
    }

    @Override
    public void update(Integer idTodo, Todo dto) {
        final Optional<Todo> t = findById(idTodo);
        t.ifPresent(todo -> {
            todo.setDone(dto.getDone());
            todo.setTitle(dto.getTitle());
            dao.save(todo);
        });
    }

    @Override
    public void delete(Integer idTodo) {
        dao.deleteById(idTodo);
    }


}
