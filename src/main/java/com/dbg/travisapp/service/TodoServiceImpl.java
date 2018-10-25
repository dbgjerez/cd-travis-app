package com.dbg.travisapp.service;

import com.dbg.travisapp.dao.TodoDAO;
import com.dbg.travisapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDAO dao;

    @Override
    public List<Todo> findAll() {
        return dao.findAll();
    }

}
