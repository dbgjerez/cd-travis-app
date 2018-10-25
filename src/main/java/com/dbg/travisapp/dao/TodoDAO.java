package com.dbg.travisapp.dao;

import com.dbg.travisapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDAO extends JpaRepository<Todo, Integer> {

}
