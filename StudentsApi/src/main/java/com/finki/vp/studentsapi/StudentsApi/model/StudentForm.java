package com.finki.vp.studentsapi.StudentsApi.model;

public class StudentForm {
    String index;
    String name;
    String lastName;
    String studyProgramName;

    public StudentForm(){}

    public StudentForm(String index, String name, String lastName, String studyProgramName) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.studyProgramName = studyProgramName;
    }

    public String getIndex() {
        return index;
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

    public String getStudyProgramName() {
        return studyProgramName;
    }

    public void setStudyProgramName(String studyProgramName) {
        this.studyProgramName = studyProgramName;
    }
}
