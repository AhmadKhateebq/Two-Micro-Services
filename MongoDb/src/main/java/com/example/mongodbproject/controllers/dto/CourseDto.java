package com.example.mongodbproject.controllers.dto;

import com.example.mongodbproject.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class CourseDto {
    protected String code;
    protected String name;
    protected int teacherUid;
    @Override
    public String toString() {
        return code + " : " + name;
    }


}
