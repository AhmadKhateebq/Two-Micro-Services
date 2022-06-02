package com.example.mongodbproject.controllers;

import com.example.mongodbproject.controllers.dto.CourseDto;
import com.example.mongodbproject.controllers.mapper.MapStruct;
import com.example.mongodbproject.model.Course;
import com.example.mongodbproject.model.CourseDetailed;
import com.example.mongodbproject.model.Teacher;
import com.example.mongodbproject.services.CourseService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("course")
public class CourseController {
    String securityHeader ;
    final String teacher_uri = "http://localhost:8082/teacher/";
    @Autowired
    CourseService service;

    MapStruct mapper = MapStruct.INSTANCE;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public List<CourseDto> getAll() {
        return service.getAllCourses ()
                .stream ()
                .map (mapper::COURSE_DTO)
                .collect (Collectors.toList ());
    }

    @GetMapping("/d")
    public List<CourseDetailed> getAllDetailed(@RequestHeader Map<String, String> headers ) {
        headers.forEach ((v1,v2)->{
            System.out.println (v1+" "+v2);
            if(v1.equals ("authorization"))
                securityHeader = v2;
        });
        System.out.println (securityHeader+"sec head");

        return service.getAllCourses ()
                .stream ()
                .map (course -> new CourseDetailed (
                        mapper.COURSE_DTO (course),
//                        restTemplate.getForObject (teacher_uri + course.getTeacherUid (), Teacher.class))
                        restTemplate.exchange
                                (teacher_uri + course.getTeacherUid (),
                                        HttpMethod.GET,
                                        new HttpEntity<String> (createHeaders())
                                        , Teacher.class
                                ).getBody ()
                )).collect(Collectors.toList());
    }

    @PostMapping("/")
    public String addCourse(@RequestBody CourseDto courseDto) {
        service.addCourse (Course.DTO (courseDto));
        return courseDto + " added";
    }


    @GetMapping("/c/{code}")
    public CourseDto getByCode(@PathVariable String code) {
        return mapper.COURSE_DTO (service.getByCode (code));
    }

    @GetMapping("/c/d/{code}")
    public CourseDetailed getByCodeDetailed(@PathVariable String code) {
        CourseDto course = mapper.COURSE_DTO (service.getByCode (code));
        Teacher teacher = restTemplate.getForObject (teacher_uri + course.getTeacherUid (), Teacher.class);
        return new CourseDetailed (course, teacher);
    }

    @GetMapping("/n/{name}")
    public List<CourseDto> getByName(@PathVariable String name) {
        return service.getAllByName (name)
                .stream ()
                .map (mapper::COURSE_DTO)
                .collect (Collectors.toList ());
    }

    @DeleteMapping("/c/{code}")
    public String deleteByCode(@PathVariable String code) {
        service.deleteByCode (code);
        return "Deleted";
    }

    @DeleteMapping("/n/{name}")
    public String deleteAllByName(@PathVariable String name) {
//        if (service.deleteAllByName (name))
//            return "all Course with code " + name + " deleted";
//        else
//            return "Course not found";
        service.deleteAllByName (name);
        return "deleted";
    }
    HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "Authorization", securityHeader );
        }};
    }

}
