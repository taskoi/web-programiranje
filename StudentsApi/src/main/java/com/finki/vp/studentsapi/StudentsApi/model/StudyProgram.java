package com.finki.vp.studentsapi.StudentsApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="StudyProgram")
public class StudyProgram {

    @Id
    @GeneratedValue
    private Long id;

    public String name;

    public StudyProgram(String name) {
        this.name = name;
    }
    public StudyProgram(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
