package com.vansh.spring.demo.service;

import com.vansh.spring.demo.dto.Note;
import com.vansh.spring.demo.entity.NoteEntity;

import java.util.List;

public interface NotesService {

    public Note findById(int id);

    public void deleteById(int id);


}
