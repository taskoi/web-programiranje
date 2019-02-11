package com.finki.vp.studentsapi.StudentsApi.service;

import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import com.finki.vp.studentsapi.StudentsApi.model.StudyProgramForm;
import com.finki.vp.studentsapi.StudentsApi.persistance.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class StudyProgramService {
    @Autowired
    StudyProgramRepository studyProgramRepository;

    public StudyProgram findByName(String name) {
        return studyProgramRepository.findByName(name);
    }

    public List<StudyProgram> findAll() {
        return studyProgramRepository.findAll();
    }

    public StudyProgram addStudyProgram(StudyProgram studyProgram) {
        return studyProgramRepository.save(studyProgram);
    }

    @Transactional
    public void deleteStudyProgram(Long id) {
        studyProgramRepository.deleteById(id);
    }

    public Optional<StudyProgram> findById(Long id) {
        return studyProgramRepository.findById(id);
    }

    public StudyProgram updateStudyProgram(StudyProgramForm studyProgramForm) {
        Optional<StudyProgram> studyProgramOptional = studyProgramRepository.findById(studyProgramForm.getId());
        if (studyProgramOptional.isPresent()) {
            StudyProgram studyProgram = studyProgramOptional.get();
            if (studyProgramForm.getName() != null) {
                studyProgram.setName(studyProgramForm.getName());
            }
            return studyProgramRepository.save(studyProgram);
        }

        return null;
    }

}
