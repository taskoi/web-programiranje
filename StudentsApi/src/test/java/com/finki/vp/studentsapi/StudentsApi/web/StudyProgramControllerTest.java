package com.finki.vp.studentsapi.StudentsApi.web;

import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgramForm;
import com.finki.vp.studentsapi.StudentsApi.service.StudyProgramService;
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

class StudyProgramControllerTest {

    @Mock
    StudyProgramService studyProgramService;

    @InjectMocks
    StudyProgramController studyProgramController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studyProgramController).build();
    }

    @Test
    void studyProgramList() throws  Exception{
        StudyProgram s1 = new StudyProgram("KNI");
        StudyProgram s2 = new StudyProgram("PET");
        List<StudyProgram> studyPrograms = Arrays.asList(
                s1,s2
        );
        Mockito.when(studyProgramService.findAll()).thenReturn(studyPrograms);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/study_programs");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(),mockHttpServletResponse.getStatus());
    }

    @Test
    void addStudyProgram() throws Exception {
        // given
        StudyProgram studyProgram = new StudyProgram("PET");
        Mockito.when(studyProgramService.addStudyProgram(Mockito.any(StudyProgram.class))).thenReturn(studyProgram);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/study_programs")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(getStudyProgramInJson("PET"));

        // when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // then
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(), mockHttpServletResponse.getStatus());
    }

    private String getStudyProgramInJson(String name) {
        return "{\"id\":\"" + null + "\", \"name\":\"" + name + "\"}";
    }

    @Test
    void updateStudyProgram() throws Exception {
        //given
        StudyProgram studyProgram = new StudyProgram("KNI");

        Mockito.when(studyProgramService.updateStudyProgram(Mockito.any(StudyProgramForm.class))).thenReturn(studyProgram);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/study_programs/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getStudyProgramFormInJson("KNI"));
        System.out.println(getStudyProgramFormInJson("KNI"));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.CREATED.value(),mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("KNI"));
    }

    private String getStudyProgramFormInJson(String name){
        return "{\"id\":\"" + 1 + "\", \"name\":\"" + name + "\"}";
    }

    @Test
    void deleteProgram() throws  Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/study_programs/1");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
        Mockito.verify(studyProgramService).deleteStudyProgram(1L);
    }


}