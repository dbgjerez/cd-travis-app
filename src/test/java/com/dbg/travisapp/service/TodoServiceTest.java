package com.dbg.travisapp.service;

import com.dbg.travisapp.dao.TodoDAO;
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

    @InjectMocks
    private TodoServiceImpl service;
    @Mock
    private TodoDAO todoDAO;

    @Test
    public void findAll() {
        final Todo todo = new Todo();
        final PageImpl mockPage = new PageImpl(Arrays.asList(todo));
        Mockito.when(todoDAO.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class))).thenReturn(mockPage);
        final Page<Todo> page = service.findAll(PageRequest.of(0, 10), Optional.ofNullable(true));
        Assert.assertEquals(page.getTotalElements(), 1L);
    }

}
