package com.example.mongodbproject.model;

import com.example.mongodbproject.controllers.dto.CourseDto;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString(exclude = {"id"}, includeFieldNames = false)
public class Course {
    @Id
     String id;
     String code;
     String name;
    int teacherUid;
    public Course() {
    }

    public Course(String code, String name,int teacherUid) {
        this.code = code;
        this.name = name;
        this.teacherUid = teacherUid;
    }

    public static Course DTO(CourseDto courseDto) {
        return new Course (courseDto.getCode (), courseDto.getName (), courseDto.getTeacherUid ());
    }

}
