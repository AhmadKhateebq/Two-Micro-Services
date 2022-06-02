package com.example.TwoSqlDB.teacher.repository;

import com.example.TwoSqlDB.teacher.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUid(int uid);
}