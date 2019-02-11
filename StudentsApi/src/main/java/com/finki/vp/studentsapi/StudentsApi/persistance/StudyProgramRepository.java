package com.finki.vp.studentsapi.StudentsApi.persistance;

import com.finki.vp.studentsapi.StudentsApi.model.StudyProgram;
import jdk.nashorn.internal.ir.Optimistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram,Long> {

    public StudyProgram findById(String id);

    public StudyProgram findByName(String name);
}
