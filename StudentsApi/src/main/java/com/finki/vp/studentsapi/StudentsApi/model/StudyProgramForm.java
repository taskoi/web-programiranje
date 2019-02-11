package com.finki.vp.studentsapi.StudentsApi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class StudyProgramForm {


    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudyProgramForm(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public StudyProgramForm(){

    }
}

