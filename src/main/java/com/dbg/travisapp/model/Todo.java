package com.dbg.travisapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Boolean done = Boolean.FALSE;
    @CreatedDate
    private Long createdDate;
    @LastModifiedDate
    private Long modifiedDate;

}
