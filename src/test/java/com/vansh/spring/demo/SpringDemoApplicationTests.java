package com.vansh.spring.demo;

import com.vansh.spring.demo.dao.TaskRepository;
import com.vansh.spring.demo.dto.Task;
import com.vansh.spring.demo.entity.TaskEntity;
import com.vansh.spring.demo.service.TaskServiceImpl;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(JMockit.class)
public class SpringDemoApplicationTests {

    private TaskRepository taskRepository;
    private TaskServiceImpl taskService;

    @Before
    public void setup(){
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService=new TaskServiceImpl();
        ReflectionTestUtils.setField(taskService, "taskRepository", taskRepository);

        TaskEntity tempTask=new TaskEntity();
        tempTask.setId(101);
        tempTask.setTitle("testing1");
        tempTask.setStatus("Not Started");
        tempTask.setStartDate(Date.valueOf("2012-01-01"));
        tempTask.setEndDate(Date.valueOf("2012-02-01"));
        tempTask.setNotes(new ArrayList<>());
        List<TaskEntity> list = new ArrayList<>();
        list.add(tempTask);
        Mockito.when(taskRepository.findAll()).thenReturn(list);
        Mockito.when(taskRepository.findById(Matchers.anyInt())).thenReturn(Optional.of(tempTask));
        Mockito.when(taskRepository.save(Matchers.any())).thenReturn(tempTask);
    }

    @Test
    public void testFindAllTask(){
        List<Task> updatedTaskList = taskService.findAll();
        Assert.assertEquals(1, updatedTaskList.size());
    }

    @Test
    public void testFindByIDTask(){
        Task task = taskService.findById(123);
        Assert.assertEquals("testing1", task.getTitle());
    }

    @Test
    public void testSaveTask(){
        int taskId= taskService.save(new Task(), null);
        Assert.assertEquals(101, taskId);
    }

}
