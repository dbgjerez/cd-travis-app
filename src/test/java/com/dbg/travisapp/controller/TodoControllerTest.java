package com.dbg.travisapp.controller;

import com.dbg.travisapp.dummy.TodoDummy;
import com.dbg.travisapp.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

    private static final Integer TODO_ID = 1;

    private MockMvc mockMvc;

    @InjectMocks
    private TodoController todoController = new TodoController();
    @Mock
    private TodoService todoService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void testFindByIdNotExists() throws Exception {
        when(todoService.findById(TODO_ID)).thenReturn(Optional.empty());
        mockMvc.perform(get("/todo/1")).andExpect(status().isNotFound());
    }

    @Test
    public void testFindByIdExists() throws Exception {
        when(todoService.findById(TODO_ID)).thenReturn(Optional.ofNullable(TodoDummy.create(TITLE)));
        mockMvc.perform(get("/todo/1")).andExpect(status().isNotFound());
    }

}
