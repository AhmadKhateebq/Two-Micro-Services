package com.example.TwoSqlDB;

import com.example.TwoSqlDB.teacher.model.Teacher;
import com.example.TwoSqlDB.teacher.repository.TeacherRepository;
import com.example.TwoSqlDB.student.model.Student;
import com.example.TwoSqlDB.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    TeacherRepository employeeRepository;

    @Autowired
    StudentRepository managerRepository;
    @RequestMapping(value="/primary", method=RequestMethod.GET)
    public List<Teacher> getPrimaryDatabaseData() {
        List<Teacher> list = employeeRepository.findAll();
        return list;
    }

    @RequestMapping(value="/secondary", method=RequestMethod.GET)
    public List<Student> getSecondaryDatabaseData() {
        List<Student> list = managerRepository.findAll();
        return list;
    }


}
