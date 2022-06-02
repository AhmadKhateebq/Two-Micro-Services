package com.example.TwoSqlDB.student.service;

import com.example.TwoSqlDB.student.model.Student;
import com.example.TwoSqlDB.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository repository;
    public void addStudent(Student student) {
        repository.save (student);
    }

    public List<Student> getAllStudents() {
        return repository.findAll ();
    }

    public Student getByUid(int uid) {
        return repository.findByUid (uid);
    }

    public void deleteStudent(Student student) {
        repository.delete (student);
    }

}
