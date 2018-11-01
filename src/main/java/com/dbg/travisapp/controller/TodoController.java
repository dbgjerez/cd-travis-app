package com.dbg.travisapp.controller;

import com.dbg.travisapp.model.Todo;
import com.dbg.travisapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public Page<Todo> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                              @RequestParam(defaultValue = "10", required = false) Integer size,
                              @RequestParam(defaultValue = "false", required = false) Optional<Boolean> done) {
        return todoService.findAll(PageRequest.of(page, size), done);
    }

    @GetMapping("/{idTodo}")
    public ResponseEntity<Todo> findById(@PathVariable Integer idTodo) {
        return todoService.findById(idTodo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @PutMapping("/{idTodo}")
    public void update(@PathVariable Integer idTodo,
                       @RequestBody Todo todo) {
        todoService.update(idTodo, todo);
    }

    @DeleteMapping("/{idTodo}")
    public void delete(@PathVariable Integer idTodo) {
        todoService.delete(idTodo);
    }

}