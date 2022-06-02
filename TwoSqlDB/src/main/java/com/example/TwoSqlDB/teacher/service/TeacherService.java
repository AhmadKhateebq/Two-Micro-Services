package com.example.TwoSqlDB.teacher.service;

import com.example.TwoSqlDB.teacher.model.Teacher;
import com.example.TwoSqlDB.teacher.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository repository;

    public void addTeacher(Teacher teacher) {
        repository.save (teacher);
    }

    public List<Teacher> getAllTeachers() {
        return repository.findAll ();
    }

    public Teacher getByUid(int uid) {
        return repository.findByUid (uid);
    }

    public void deleteTeacher(Teacher teacher) {
        repository.delete (teacher);
    }

}
