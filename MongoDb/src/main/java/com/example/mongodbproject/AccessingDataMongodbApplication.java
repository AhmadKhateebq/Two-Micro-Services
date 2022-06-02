package com.example.mongodbproject;

import com.example.mongodbproject.model.Course;
import com.example.mongodbproject.repoistory.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessingDataMongodbApplication //implements CommandLineRunner {
{
    @Autowired
    private CourseRepository repository;

    public static void main(String[] args) {
        SpringApplication.run (AccessingDataMongodbApplication.class, args);
    }

    //    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll ();

        // save a couple of customers
        repository.save (new Course ("comp333", "database",111));
        repository.save (new Course ("comp242", "data structure",222));

        // fetch all customers
        System.out.println ("Customers found with findAll():");
        System.out.println ("-------------------------------");
        for (Course customer : repository.findAll ()) {
            System.out.println (customer);
        }
        System.out.println ();

        // fetch an individual customer
        System.out.println ("Customer found with findByFirstName('Alice'):");
        System.out.println ("--------------------------------");
        System.out.println (repository.findAllByCode ("Alice"));

        System.out.println ("Customers found with findByLastName('Smith'):");
        System.out.println ("--------------------------------");
        for (Course customer : repository.findAllByName ("Smith")) {
            System.out.println (customer);
        }

    }

}