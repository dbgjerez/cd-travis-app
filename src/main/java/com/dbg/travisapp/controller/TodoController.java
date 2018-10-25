package com.dbg.travisapp.controller;

import com.dbg.travisapp.model.Todo;
import com.dbg.travisapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> findAll(){
        return todoService.findAll();
    }

}
