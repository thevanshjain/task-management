package com.vansh.spring.demo.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class Note {

    private int id;
    private String description;
    private Date createdAt;
    private Date modifiedAt;
    private int taskId;

    public Note() {
        this.createdAt = new Date(System.currentTimeMillis());
        this.modifiedAt = new Date(System.currentTimeMillis());
    }
}