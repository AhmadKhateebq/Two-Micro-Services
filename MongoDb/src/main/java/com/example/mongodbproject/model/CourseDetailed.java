package com.example.mongodbproject.model;

import com.example.mongodbproject.controllers.dto.CourseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseDetailed extends CourseDto {
    Teacher teacher;

    public CourseDetailed(CourseDto course, Teacher teacher) {

        super(course.getCode (),course.getName (),course.getTeacherUid ());
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "CourseDetailed{" +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", teacherUid=" + teacherUid +
                ", teacher=" + teacher +
                '}';
    }
}
