package com.example.TwoSqlDB.student.repository;

import com.example.TwoSqlDB.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
        Student findByUid(int uid);
}