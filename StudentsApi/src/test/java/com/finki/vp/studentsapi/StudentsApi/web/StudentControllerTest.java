package com.finki.vp.studentsapi.StudentsApi.web;

import com.finki.vp.studentsapi.StudentsApi.model.Student;
import com.finki.vp.studentsapi.StudentsApi.model.StudentForm;
import com.finki.vp.studentsapi.StudentsApi.model.StudentWithoutStudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.service.StudentService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

class StudentControllerTest {

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }
    @Test
    void getAll() throws Exception{
        StudentWithoutStudyProgram student1 = new StudentWithoutStudyProgram();
        student1.setIndex("151146");
        student1.setName("Ivan");
        student1.setLastName("Tasevski");
        StudentWithoutStudyProgram student2 = new StudentWithoutStudyProgram();
        student2.setIndex("111111");
        student2.setName("Krste");
        student2.setLastName("Miladinov");

        List<StudentWithoutStudyProgram> studentsList = Arrays.asList(
                student1,student2
        );

        Mockito.when(studentService.getAll()).thenReturn(studentsList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("151146"));
    }

    @Test
    void findByIndex() throws  Exception{
        Student student = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        Mockito.when(studentService.findByIndex("151146")).thenReturn(student);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students/151146");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("151146"));
    }

    @Test
    void findByStudyProgramId() throws  Exception{
        Student s1 = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        List<Student> students = Arrays.asList(s1);
        Mockito.when(studentService.findByStudyProgramId(1L)).thenReturn(students);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/students/by_study_program/1");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("151146"));
    }

    @Test
    void createStudent() throws  Exception{
        StudyProgram studyProgram = new StudyProgram("PET");
        Student student = new Student("151146","Ivan","Tasevski",studyProgram);

        Mockito.when(studentService.createStudent(Mockito.any(StudentForm.class))).thenReturn(student);
        System.out.println(getStudentFormInJson("151146","Ivan","Tasevski","PET"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/students")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getStudentFormInJson("151146","Ivan","Tasevski","PET"));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(),mockHttpServletResponse.getStatus());
    }

    private String getStudentFormInJson(String index,String name,String lastName,String studyProgramName){
        return "{\"index\":\"" + index + "\", \"name\":\"" + name + "\", " +
                "\"lastName\":\"" + lastName + "\", \"studyProgramName\":\"" + studyProgramName + "\"}";
    }
    @Test
    void updateStudent() throws Exception{

        Student student = new Student("151146","Bojan","Tasevski",new StudyProgram("KNI"));

        Mockito.when(studentService.updateStudent(Mockito.any(StudentForm.class))).thenReturn(student);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/students/151146")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getStudentFormInJson(" 151146","Bojan","Tasevski","KNI"));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(),mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("KNI"));
    }

    @Test
    void deleteStudent() throws Exception{
        Student student = new Student("151146","Ivan","Tasevski",new StudyProgram("PET"));
        Mockito.when(studentService.findByIndex("151146")).thenReturn(student);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/students/151146");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),mockHttpServletResponse.getStatus());
    }


}