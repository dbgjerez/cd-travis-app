package com.dbg.travisapp.service;

import com.dbg.travisapp.dao.TodoDAO;
import com.dbg.travisapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO dao;

    @Override
    public Page<Todo> findAll(PageRequest pageable,
                              Optional<Boolean> done) {
        final Todo todo = new Todo();
        done.ifPresent(todo::setDone);
        final Example<Todo> example = Example.of(todo);
        return dao.findAll(example, pageable);
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
    public void update(Integer idTodo,
                       Todo dto) {
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
