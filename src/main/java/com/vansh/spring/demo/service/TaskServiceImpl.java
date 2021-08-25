package com.vansh.spring.demo.service;

import com.vansh.spring.demo.dao.TaskRepository;
import com.vansh.spring.demo.dto.Note;
import com.vansh.spring.demo.dto.Task;
import com.vansh.spring.demo.entity.NoteEntity;
import com.vansh.spring.demo.entity.TaskEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
        public List<Task> findAll() {
        List<Task> tasks= new ArrayList<>();
        for(TaskEntity taskEntity : taskRepository.findAll()){
           Task task = new Task();
           BeanUtils.copyProperties(taskEntity,task);
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public Task findById(int id) {
        Optional<TaskEntity> result=taskRepository.findById(id);

        TaskEntity taskEntity;
        if (result.isPresent()) {
            taskEntity=result.get();
        }
        else{
            throw new RuntimeException("Invalid tasks id -- "+id);
        }
        Task task = new Task();
        List<Note> notes = new ArrayList<>();
        BeanUtils.copyProperties(taskEntity,task);
        for (NoteEntity noteEntity : taskEntity.getNotes()){
            Note note = new Note();
            BeanUtils.copyProperties(noteEntity, note);
            notes.add(note);
        }
        task.setTaskNotes(notes);
        return task;
    }

    @Override
    public int save(Task task, Note note) {
        TaskEntity taskEntity = new TaskEntity();
        BeanUtils.copyProperties(task,taskEntity);
        if (null != note) {
            NoteEntity noteEntity = new NoteEntity();
            BeanUtils.copyProperties(note, noteEntity);
            taskEntity.addNotes(noteEntity);
        }
        TaskEntity t = taskRepository.save(taskEntity);
        return t.getId();
    }

    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }
}
