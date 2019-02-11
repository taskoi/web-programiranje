package com.finki.vp.studentsapi.StudentsApi.web;

import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgramForm;
import com.finki.vp.studentsapi.StudentsApi.service.StudyProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudyProgramController {

    @Autowired
    private StudyProgramService studyProgramService;

    @GetMapping("/study_programs")
    @ResponseStatus(HttpStatus.OK)
    public List<StudyProgram> studyProgramList() {
        return studyProgramService.findAll();
    }

    @PostMapping("/study_programs")
    @ResponseStatus(HttpStatus.CREATED)
    public StudyProgram addStudyProgram(@RequestBody StudyProgram studyProgram) {
        return studyProgramService.addStudyProgram(studyProgram);
    }

    @DeleteMapping("/study_programs/{id}")
    public void deleteProgram(@PathVariable Long id) {
//        Optional<StudyProgram> studyProgram = studyProgramService.findById(id);
        studyProgramService.deleteStudyProgram(id);
    }

    @PatchMapping("/study_programs/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public StudyProgram updateStudyProgram(@PathVariable String id, @RequestBody StudyProgramForm studyProgramForm) {
        return studyProgramService.updateStudyProgram(studyProgramForm);
    }

}
