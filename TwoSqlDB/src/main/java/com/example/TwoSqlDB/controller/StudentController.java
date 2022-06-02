package com.example.TwoSqlDB.controller;

import com.example.TwoSqlDB.teacher.model.Teacher;
import com.example.TwoSqlDB.teacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

    @RestController
    @RequestMapping("teacher")
public class StudentController {
    @Autowired
    TeacherService service;
    @GetMapping("/{uid}")
    Teacher getByUid(@PathVariable int uid){
        return service.getByUid (uid);
    }
    @GetMapping()
    List<Teacher> getAll(){
        return service.getAllTeachers ();
    }
}
