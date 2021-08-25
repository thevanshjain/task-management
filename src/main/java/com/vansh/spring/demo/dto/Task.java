package com.vansh.spring.demo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;

@Data
public class Task {

    private int id;

    @NotNull
    private String title;
    @NotNull
    private String status;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

    private List<Note> taskNotes;
}
