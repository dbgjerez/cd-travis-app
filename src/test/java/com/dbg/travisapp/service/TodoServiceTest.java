package com.dbg.travisapp.service;

import com.dbg.travisapp.dao.TodoDAO;
import com.dbg.travisapp.dummy.TodoDummy;
import com.dbg.travisapp.model.Todo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    public static final String TITLE = "Comer";
    public static final int ID = 1;
    public static final String SUB_TITLE = "a";

    @InjectMocks
    private TodoServiceImpl service;
    @Mock
    private TodoDAO todoDAO;

    @Test
    public void findById() {
        final Todo todo = TodoDummy.create(TITLE, Boolean.FALSE);
        todo.setId(ID);
        Mockito.when(todoDAO.findById(ID)).thenReturn(Optional.ofNullable(todo));
        Assert.assertEquals(todo, service.findById(ID).get());
    }

    @Test
    public void findAll() {
        final PageImpl mockPage = new PageImpl(Arrays.asList(TodoDummy.create()));
        Mockito.when(todoDAO.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class))).thenReturn(mockPage);
        final Page<Todo> page = service.findAll(PageRequest.of(0, 10), Optional.ofNullable(true));
        Assert.assertEquals(page.getTotalElements(), 1L);
    }

    @Test
    public void testCreate() {
        final Todo todo = TodoDummy.create(TITLE, Boolean.FALSE);
        final Todo created = TodoDummy.create(TITLE, Boolean.FALSE);
        created.setId(1);
        Mockito.when(todoDAO.save(todo)).thenReturn(created);
        final Todo res = service.create(todo);
        Assert.assertNotNull(res);
        Assert.assertEquals(TITLE, res.getTitle());
        Assert.assertEquals(Boolean.FALSE, res.getDone());
        Assert.assertNotNull(res.getId());
    }

    @Test
    public void testUpdateExists() {
        final Todo todo = Mockito.mock(Todo.class);
        Mockito.when(todoDAO.findById(ID)).thenReturn(Optional.ofNullable(todo));
        service.update(ID, todo);
        Mockito.verify(todoDAO).findById(ID);
        Mockito.verify(todo).setTitle(Mockito.any());
        Mockito.verify(todo).setDone(Mockito.any());
        Mockito.verify(todo, Mockito.times(0)).setId(Mockito.any());
        Mockito.verify(todoDAO).save(todo);
    }

    @Test
    public void testUpdateNotExists() {
        final Todo todo = Mockito.mock(Todo.class);
        Mockito.when(todoDAO.findById(ID)).thenReturn(Optional.empty());
        service.update(ID, todo);
        Mockito.verify(todoDAO).findById(ID);
        Mockito.verify(todo, Mockito.times(0)).setTitle(Mockito.any());
        Mockito.verify(todo, Mockito.times(0)).setDone(Mockito.any());
        Mockito.verify(todo, Mockito.times(0)).setId(Mockito.any());
        Mockito.verify(todoDAO, Mockito.times(0)).save(todo);
    }

    @Test
    public void testDelete() {
        service.delete(ID);
        Mockito.verify(todoDAO).deleteById(ID);
    }


}
