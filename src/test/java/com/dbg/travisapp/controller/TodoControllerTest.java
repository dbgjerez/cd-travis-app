package com.dbg.travisapp.controller;

import com.dbg.travisapp.dummy.TodoDummy;
import com.dbg.travisapp.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static com.dbg.travisapp.dummy.Dummy.toJSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

  private static final Integer TODO_ID = 1;
  private static final String TITLE = "Prueba";
  private static final String URL_PATH = "/todo";

  private MockMvc mockMvc;

  @InjectMocks private TodoController todoController = new TodoController();
  @Mock private TodoService todoService;

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
    when(todoService.findById(TODO_ID))
        .thenReturn(Optional.ofNullable(TodoDummy.create(TITLE, Boolean.FALSE)));
    mockMvc
        .perform(get("/todo/1"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(TITLE)));
  }

  @Test
  public void testUpdate() throws Exception {
    mockMvc
        .perform(
            put("/todo/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSON(TodoDummy.create(TITLE, Boolean.TRUE))))
        .andExpect(status().isOk());
  }

  @Test
  public void testCreate() throws Exception {
    when(todoService.create(any())).thenReturn(TodoDummy.create(TODO_ID, TITLE, Boolean.FALSE));
    mockMvc
        .perform(
            post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJSON(TodoDummy.create())))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString(TITLE)))
        .andExpect(content().string(containsString(TODO_ID.toString())));
  }

  @Test
  public void testDelete() throws Exception {
    mockMvc
        .perform(delete("/todo/" + TODO_ID).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    verify(todoService).delete(TODO_ID);
  }

  @Test
  public void testFindAll() throws Exception {
    when(todoService.findAll(any(), any()))
        .thenReturn(new PageImpl<>(Arrays.asList(TodoDummy.create(), TodoDummy.create())));
    mockMvc
        .perform(get(URL_PATH).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}
