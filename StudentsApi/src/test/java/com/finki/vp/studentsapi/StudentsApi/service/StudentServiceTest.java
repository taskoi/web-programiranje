package com.finki.vp.studentsapi.StudentsApi.service;

import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentIndexLengthException;
import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentMissingParametarException;
import com.finki.vp.studentsapi.StudentsApi.exceptions.StudentNotExistException;
import com.finki.vp.studentsapi.StudentsApi.model.Student;
import com.finki.vp.studentsapi.StudentsApi.model.StudentForm;
import com.finki.vp.studentsapi.StudentsApi.model.StudentWithoutStudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.persistance.StudentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Mock
    StudyProgramService studyProgramService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAll() {
        Student student = new Student("151146","Ivan","Tasevski",null);

        List<Student> studentList = Arrays.asList(student);

        Mockito.when(studentRepository.findAll()).thenReturn(studentList);

        List<StudentWithoutStudyProgram> result = studentService.getAll();

        Assert.assertEquals("151146",result.get(0).getIndex());
    }

    @Test
    void findByIndex() {
        Student student = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        Mockito.when(studentRepository.findByIndex("151146")).thenReturn(student);

        Student result = studentService.findByIndex("151146");

        Assert.assertEquals("151146",result.getIndex());
    }

    @Test
    void findByStudyProgramId() {
        Student student = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        Mockito.when(studentRepository.findByStudyProgramId(1L)).thenReturn(Arrays.asList(student));

        List<Student> result = studentService.findByStudyProgramId(1L);

        Assert.assertEquals("151146",result.get(0).getIndex());
    }

    @Test
    void createStudentWithoutExceptionsHandled() {
        StudentForm studentForm = new StudentForm("151146","Ivan","Tasevski","PET");
        StudyProgram studyProgram = new StudyProgram("KNI");
        Mockito.when(studyProgramService.findByName(studentForm.getName())).thenReturn(studyProgram);
        Student studen = new Student("151146","Ivan","Tasevski",studyProgram);
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(studen);
        Mockito.when(studyProgramService.findByName(studentForm.getStudyProgramName())).thenReturn(studyProgram);
        Student result = studentService.createStudent(studentForm);

        Assert.assertEquals("151146",result.getIndex());
        Assert.assertEquals("Ivan",result.getName());
        Assert.assertEquals("KNI",result.getStudyProgram().getName());
    }

    @Test
    void createStudentWithExceptionMissingName(){
        // given
        StudentForm studentFormWithoutName = new StudentForm("151146", "", "Tasevski", "PET");

        assertThrows(StudentMissingParametarException.class, () -> {
            // when
            studentService.createStudent(studentFormWithoutName);
        });
    }

    @Test
    void createStudentWithExceptionMissingProgramName(){
        StudentForm studentForm = new StudentForm("151146","IVan","Tasevski","");

        assertThrows(StudentMissingParametarException.class, () -> {
            studentService.createStudent(studentForm);
        });
    }

    @Test
    void createStudentWithExceptionMissingLastName(){
        StudentForm studentForm = new StudentForm("151146","IVan","","PET");

        assertThrows(StudentMissingParametarException.class, () -> {
            studentService.createStudent(studentForm);
        });
    }
    @Test
    void createStudentWithExceptionMissingIndex(){
        StudentForm studentForm = new StudentForm("","IVan","Tasevski","PET");

        assertThrows(StudentMissingParametarException.class, () -> {
            studentService.createStudent(studentForm);
        });
    }

    @Test
    void createStudentWithExceptionIndexLengthNotTrue(){
        StudentForm studentForm = new StudentForm("123","IVan","Tasevski","PET");

        assertThrows(StudentIndexLengthException.class, () -> {
            studentService.createStudent(studentForm);
        });
    }

    @Test
    void findAll() {
        List<Student> list = Arrays.asList( new Student("151146","Ivan","Tasevski",new StudyProgram("PET")));
        Mockito.when(studentRepository.findAll()).thenReturn(list);

        List<Student> result = studentService.findAll();

        Assert.assertEquals("151146",result.get(0).getIndex());
    }

    @Test
    void updateStudent() {
        Student student = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        Mockito.when(studentRepository.findByIndex("151146")).thenReturn(student);
        StudentForm studentForm = new StudentForm("151146","Ivan","Tasevski","PET");
        Mockito.when(studyProgramService.findByName(studentForm.getStudyProgramName())).thenReturn(new StudyProgram("PET"));
        Mockito.when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.updateStudent(studentForm);

        Assert.assertEquals("PET",result.getStudyProgram().getName());
    }

    @Test
    void updateStudentStudentNull(){
        StudentForm studentForm = new StudentForm();
       // Mockito.when(studentRepository.findByIndex("151146")).thenReturn(null);
        assertThrows(StudentNotExistException.class, () ->{
            studentService.updateStudent(studentForm);
        });
    }

    @Test
    void deleteStudent() {
        studentService.deleteStudent("151146");

        Mockito.verify(studentRepository).deleteById("151146");
    }
}