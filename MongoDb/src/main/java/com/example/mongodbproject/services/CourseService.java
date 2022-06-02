package com.example.mongodbproject.services;

import com.example.mongodbproject.model.Course;
import com.example.mongodbproject.repoistory.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository repository;

    public void addCourse(Course course) {
        repository.save (course);
    }

    public List<Course> getAllCourses() {
        return repository.findAll ();
    }

    public Course getByCode(String code) {
        return repository.findAllByCode (code);
    }

    public List<Course> getAllByName(String name) {
        return repository.findAllByName (name);
    }

    public void deleteCourse(Course course) {
        repository.delete (course);
    }

    public void deleteByCode(String code) {
        System.out.println (repository.deleteByCode (code));
    }

    public void deleteAllByName(String name) {
        System.out.println (repository.deleteAllByName (name));
    }

}
