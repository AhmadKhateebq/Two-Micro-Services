package com.example.mongodbproject.controllers.mapper;

import com.example.mongodbproject.controllers.dto.CourseDto;
import com.example.mongodbproject.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStruct {

    MapStruct INSTANCE = Mappers.getMapper (MapStruct.class);

    @Mapping(target = "teacherUid", source = "teacherUid")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    CourseDto COURSE_DTO(Course course);

    @Mapping(target = "teacherUid", source = "teacherUid")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "name", source = "name")
    Course COURSE_DTO(CourseDto dto);

}
