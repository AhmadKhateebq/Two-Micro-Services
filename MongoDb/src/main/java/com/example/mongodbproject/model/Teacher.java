package com.example.mongodbproject.model;

import lombok.Data;

@Data
public class Teacher {
    private String name;
    private int uid;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", uid=" + uid +
                '}';
    }
}