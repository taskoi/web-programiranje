package com.finki.vp.studentsapi.StudentsApi.persistance;

import com.finki.vp.studentsapi.StudentsApi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,String > {

    public Student findByIndex(String index);

    public List<Student> findByStudyProgramId (Long id);

    public Student deleteStudentByIndex(String index);

    public List<Student> findAll();
}
