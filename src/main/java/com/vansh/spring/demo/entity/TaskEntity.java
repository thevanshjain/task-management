package com.vansh.spring.demo.entity;
import javax.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty
    @Column(name = "title")
    private String title;


    @Column(name = "status")
    private String status;

    @Column(name = "startdate")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @Column(name = "enddate")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    public TaskEntity(String title, String status) {
        this.title = title;
        this.status = status;
    }

    @OneToMany(mappedBy = "theTask",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<NoteEntity> notes;


    public void addNotes(NoteEntity note){
        if(notes==null)
            notes=new ArrayList<>();

        Iterator<NoteEntity> itr = notes.iterator();
        while (itr.hasNext()){
            NoteEntity currentNote = itr.next();
            if(currentNote.getId()==note.getId()){
                itr.remove();
                break;
            }
        }
        notes.add(note);
        note.setTheTask(this);
    }

}
