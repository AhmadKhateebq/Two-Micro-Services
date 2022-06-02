package com.example.TwoSqlDB.controller;

import com.example.TwoSqlDB.student.model.Student;
import com.example.TwoSqlDB.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("student")
public class TeacherController {
    @Autowired
    StudentService service;
    @GetMapping("/{uid}")
    Student getByUid(@PathVariable int uid){
        return service.getByUid (uid);
    }
    @GetMapping()
    List<Student> getAll(){
        return service.getAllStudents ();
    }
}
