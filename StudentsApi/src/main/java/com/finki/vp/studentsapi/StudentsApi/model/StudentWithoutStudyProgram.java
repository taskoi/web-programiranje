package com.finki.vp.studentsapi.StudentsApi.model;

public class StudentWithoutStudyProgram {
    private String index;

    private String name;

    private String lastName;

    public String getIndex() {
        return index;
    }

    public StudentWithoutStudyProgram(){

    }
    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
