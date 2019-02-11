package com.finki.vp.studentsapi.StudentsApi.web;

import com.finki.vp.studentsapi.StudentsApi.exceptions.*;
import com.finki.vp.studentsapi.StudentsApi.model.Student;
import com.finki.vp.studentsapi.StudentsApi.model.StudentForm;
import com.finki.vp.studentsapi.StudentsApi.model.StudentWithoutStudyProgram;
import com.finki.vp.studentsapi.StudentsApi.service.StudentService;
import com.finki.vp.studentsapi.StudentsApi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudyProgramService studyProgramService;

    @GetMapping("/students")
    public List<StudentWithoutStudyProgram> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/students/{id}")
    public Student findByIndex(@PathVariable String id) {
        Student student = studentService.findByIndex(id);
        if (student == null)
            throw new StudentMissingParametarException("user not found with id ->" + id);
        return studentService.findByIndex(id);
    }

    @GetMapping("/students/by_study_program/{id}")
    public List<Student> findByStudyProgramId(@PathVariable Long id) {

        return studentService.findByStudyProgramId(id);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@Valid @RequestBody StudentForm studentForm, HttpServletResponse response) {
        Student student = studentService.createStudent(studentForm);
        response.setHeader("Location", "/students/" + student.getIndex());
        return student;
    }

    @PatchMapping("/students/{index}")
    @ResponseStatus(HttpStatus.CREATED)
    public Student updateStudent(@PathVariable String index, @RequestBody StudentForm studentForm)  {
        return studentService.updateStudent(studentForm);
    }

    @DeleteMapping("/students/{index}")
    public void deleteStudent(@PathVariable String index){
      Student student = studentService.findByIndex(index);
      if(student == null)
          throw new StudentNotExistException("nepostoi");

      studentService.deleteStudent(index);
    }
}
