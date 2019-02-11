package com.finki.vp.studentsapi.StudentsApi.service;

import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgramForm;
import com.finki.vp.studentsapi.StudentsApi.persistance.StudyProgramRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class StudyProgramServiceTest {

    @Mock
    StudyProgramRepository studyProgramRepository;

    @InjectMocks
    StudyProgramService studyProgramService;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByName() {
        // given
        String name = "KNI";
        StudyProgram studyProgram = new StudyProgram();
        studyProgram.setName("KNI");
        Mockito.when(studyProgramRepository.findByName(name)).thenReturn(studyProgram);

        // when
        StudyProgram result = studyProgramService.findByName(name);

        // then
        Assert.assertEquals("KNI", result.getName());
    }

    @Test
    void findAll() {
        StudyProgram sp1 = new StudyProgram("KNI");
        StudyProgram sp2 = new StudyProgram("PET");
        List<StudyProgram> studyProgramList  = Arrays.asList(sp1,sp2);
        Mockito.when(studyProgramRepository.findAll()).thenReturn(studyProgramList);

        List<StudyProgram> list = studyProgramService.findAll();

        Assert.assertTrue(list.get(0).getName().equals("KNI"));
        Assert.assertTrue(list.get(1).getName().equals("PET"));
    }

    @Test
    void addStudyProgram() {
        StudyProgram studyProgram = new StudyProgram("ASI");
        Mockito.when(studyProgramService.addStudyProgram(Mockito.any(StudyProgram.class))).thenReturn(studyProgram);

        StudyProgram result = studyProgramService.addStudyProgram(studyProgram);

        Assert.assertEquals("ASI",result.getName());
    }

    @Test
    void deleteStudyProgram() {
        studyProgramService.deleteStudyProgram(1L);

        Mockito.verify(studyProgramRepository).deleteById(1L);
    }

    @Test
    void findById() {
        StudyProgram studyProgram = new StudyProgram("PET");
        Optional<StudyProgram> op = Optional.of(studyProgram);

        Mockito.when(studyProgramRepository.findById(1L)).thenReturn(op);

        Optional<StudyProgram> result = studyProgramService.findById(1L);

        Assert.assertEquals("PET", result.get().getName());
    }

    @Test
    void updateStudyProgramTrue() {
        StudyProgram studyProgram = new StudyProgram("PET");
        Optional<StudyProgram> op = Optional.of(studyProgram);
        StudyProgramForm studyProgramForm = new StudyProgramForm(1L,"PET");
        Mockito.when(studyProgramRepository.findById(1L)).thenReturn(op);
        Mockito.when(studyProgramRepository.save(studyProgram)).thenReturn(studyProgram);

        StudyProgram result = studyProgramService.updateStudyProgram(studyProgramForm);

        Assert.assertEquals("PET",result.getName());

    }

    @Test
    void updateStudyProgramFalse(){
        Mockito.when(studyProgramRepository.findById(2L)).thenReturn(Optional.empty());

        StudyProgramForm studyProgramForm = new StudyProgramForm(2L,"KNI");
        StudyProgram result = studyProgramService.updateStudyProgram(studyProgramForm);

        Assert.assertNull(result);
    }
}