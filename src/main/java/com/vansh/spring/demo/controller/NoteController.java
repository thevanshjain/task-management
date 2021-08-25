package com.vansh.spring.demo.controller;

import com.vansh.spring.demo.dto.Note;
import com.vansh.spring.demo.dto.Task;
import com.vansh.spring.demo.service.NotesService;
import com.vansh.spring.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Component
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NotesService noteService;
    @Autowired
    private TaskService taskService;

    private final Logger logger = LoggerFactory.getLogger(NoteController.class);

    @GetMapping("/showFormForNotes")
    public String showFormForNotes(@RequestParam("taskId") int taskId, Model model){

            Note note = new Note();
           logger.info(String.valueOf(new Date(System.currentTimeMillis())));
            model.addAttribute("notes", note);
            model.addAttribute("taskId", taskId);
            return "notes/form-note";

    }

    @PostMapping("/saveNote")
    public String saveNote(@ModelAttribute("note") Note note,@RequestParam int taskId, Model model){
        try {

            Task task = taskService.findById(taskId);
            taskService.save(task, note);
            return "redirect:/task/showNotes?id=" + taskId;

        }
        catch (RuntimeException exc){
            model.addAttribute("message",exc.getMessage());
            return "exception/invalidId";
        }

    }



    @GetMapping("/updateNote")
    public String showFormForUpdateNote(@RequestParam(name = "id") int id,Model model){
    try {
        Note note = noteService.findById(id);
        logger.info("Updating...  "+String.valueOf(note.getCreatedAt()));
        model.addAttribute("taskId", note.getTaskId());
        model.addAttribute("notes", note);

        return "notes/form-note";
    }
    catch (RuntimeException exc){
        model.addAttribute("message",exc.getMessage());
        return "exception/invalidId";
    }
    }

    @GetMapping("/deleteNote/{id}/{taskId}")
    public String deleteNote(@PathVariable int id,@PathVariable int taskId){

        noteService.deleteById(id);
        return "redirect:/task/showNotes?id="+ taskId;
    }

}
