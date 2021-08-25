package com.vansh.spring.demo.controller;

import com.vansh.spring.demo.dto.Note;
import com.vansh.spring.demo.dto.Task;
import com.vansh.spring.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/list")
    public String listTasks(Model model){

        List<Task> taskList=taskService.findAll();

        model.addAttribute("tasks",taskList);
        return "tasks/list-task";
    }

     @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

         Task task=new Task();
         List<String> listStatus = Arrays.asList("Not Started", "Ongoing", "Completed");
         model.addAttribute("listStatus", listStatus);
        model.addAttribute("task",task);
        return "tasks/form-task";

    }

    @PostMapping("/save")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult theBindingResult,Model model){

        if(theBindingResult.hasErrors() || task.getStartDate().after(task.getEndDate())){
            List<String> listStatus = Arrays.asList("Not Started", "Ongoing", "Completed");
            model.addAttribute("listStatus", listStatus);
            return "tasks/form-task";
        }
        else {
            taskService.save(task, null);
            return "redirect:/task/list";
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id,Model model){

    try {
        Task task=taskService.findById(id);
        List<String> listStatus = Arrays.asList("Not Started", "Ongoing", "Completed");
        model.addAttribute("listStatus", listStatus);
        model.addAttribute("task",task);
        return "tasks/form-task";
    }
    catch (RuntimeException exc){
        model.addAttribute("message",exc.getMessage());
        return "exception/invalidId";
    }

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        taskService.deleteById(id);
        return "redirect:/task/list";

    }

    @GetMapping("/showNotes")
    public String showNotes(@RequestParam("id") int taskId, Model model){

        try {

            Task task = taskService.findById(taskId);
            List<Note> noteList = task.getTaskNotes();
            model.addAttribute("taskName", task.getTitle());
            model.addAttribute("notes", noteList);
            model.addAttribute("taskId", taskId);
            return "notes/list-notes";
        }
        catch (RuntimeException exc){
            model.addAttribute("message",exc.getMessage());
            return "exception/invalidId";
        }
    }
}
