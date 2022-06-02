package com.example.mongodbproject.repoistory;

import com.example.mongodbproject.model.Course;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Document(collection = "courses")
public interface CourseRepository extends MongoRepository<Course, String> {

    Course findAllByCode(String code);

    List<Course> findAllByName(String name);

    Long deleteByCode(String code);

    Long deleteAllByName(String name);
}
