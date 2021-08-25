package com.vansh.spring.demo.service;

import com.vansh.spring.demo.dto.Note;
import com.vansh.spring.demo.dto.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();

    public Task findById(int id);

    public int save(Task task, Note note);

    public void deleteById(int id);


}
