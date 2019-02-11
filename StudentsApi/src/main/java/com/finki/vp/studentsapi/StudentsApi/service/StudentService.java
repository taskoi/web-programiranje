package com.finki.vp.studentsapi.StudentsApi.service;

import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentIndexLengthException;
import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentMissingParametarException;
import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentNotExistException;
import com.finki.vp.studentsapi.StudentsApi.exceptions.StudyProgramNotExistException;
import com.finki.vp.studentsapi.StudentsApi.model.Student;
import com.finki.vp.studentsapi.StudentsApi.model.StudentForm;
import com.finki.vp.studentsapi.StudentsApi.model.StudentWithoutStudyProgram;
import com.finki.vp.studentsapi.StudentsApi.persistance.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudyProgramService studyProgramService;

    public List<StudentWithoutStudyProgram> getAll() {
        return studentRepository.findAll().stream().map(student -> {
            StudentWithoutStudyProgram s = new StudentWithoutStudyProgram();
            s.setIndex(student.getIndex());
            s.setName(student.getName());
            s.setLastName(student.getLastName());
            return s;
        }).collect(Collectors.toList());
    }


    public Student findByIndex(String index) {
        return studentRepository.findByIndex(index);
    }

    public List<Student> findByStudyProgramId(Long id) {
        return studentRepository.findByStudyProgramId(id);
    }

    public Student createStudent(StudentForm studentForm) {
        if (studentForm.getName().equals("")) {
            throw new StudentMissingParametarException("Missing parameter -> Name!");
        } else if (studentForm.getStudyProgramName().equals("")) {
            throw new StudentMissingParametarException("Missing parameter -> Study Program name");
        } else if (studentForm.getLastName().equals("")) {
            throw new StudentMissingParametarException("Missing parameter -> Last name!");
        } else if (studentForm.getIndex().equals("")) {
            throw new StudentMissingParametarException("Missing parameter -> Index!");
        } else if (studentForm.getIndex().length() != 6) {
            throw new StudentIndexLengthException("The length of the index must be 6");
        } else if (studyProgramService.findByName(studentForm.getStudyProgramName()) == null) {
            throw new StudyProgramNotExistException("Study program not found");
        }
        Student student = new Student();
        student.setIndex(studentForm.getIndex());
        student.setName(studentForm.getName());
        student.setLastName(studentForm.getLastName());
        student.setStudyProgram(studyProgramService.findByName(studentForm.getStudyProgramName()));
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student updateStudent(StudentForm studentForm) {
        Student student = studentRepository.findByIndex(studentForm.getIndex());
        if (student == null) {
            throw new StudentNotExistException("Student doesn't exist");
        }
        if (studentForm.getName() != null) {
            student.setName(studentForm.getName());
        }
        if (studentForm.getLastName() != null) {
            student.setLastName(studentForm.getLastName());
        }
        if (studentForm.getStudyProgramName() != null) {
            if (studyProgramService.findByName(studentForm.getStudyProgramName()) == null) {
                throw new StudyProgramNotExistException("Study program doesn't exist");
            }
            student.setStudyProgram(studyProgramService.findByName(studentForm.getStudyProgramName()));
        }
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(String index) {
        studentRepository.deleteById(index);
    }
}
