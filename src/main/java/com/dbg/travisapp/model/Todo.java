package com.dbg.travisapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Boolean done;

}
