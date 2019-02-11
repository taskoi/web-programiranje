//package com.finki.vp.studentsapi.StudentsApi;
//
//
//import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
//import com.finki.vp.studentsapi.StudentsApi.service.StudentService;
//import com.finki.vp.studentsapi.StudentsApi.service.StudyProgramService;
//import com.finki.vp.studentsapi.StudentsApi.web.StudentController;
//import com.finki.vp.studentsapi.StudentsApi.web.StudyProgramController;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class StudentsApiApplicationTests {
//
//    @Mock
//    StudyProgramService studyProgramService;
//
//    @Mock
//    StudentService studentService;
//
//    @Mock
//    StudyProgramController studyProgramController;
//
//
//
//    MockMvc mockMvc;
//
//    @Before
//    public void init(){
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(studyProgramController).build();
//    }
//
//    @Test
//    public void test1() throws Exception{
//        StudyProgram studyProgram = new StudyProgram("PET");
//        Mockito.when(studyProgramService.addStudyProgram(Mockito.any(StudyProgram.class))).thenReturn(studyProgram);
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/study_programs")
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(getStudyProgramInJson("PET"));
//        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
//        Assert.assertEquals(HttpStatus.CREATED.value(),mockHttpServletResponse.getStatus());
//
//    }
//
//    private String getStudyProgramInJson(String name) {
//        return "{\"id\":\"" + null + "\", \"name\":\"" + name + "\"}";
//    }
//}
