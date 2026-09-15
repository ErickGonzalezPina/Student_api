package com.erick.student_api.dto;


import com.erick.student_api.enums.Course;
import com.erick.student_api.enums.Semester;

public record StudentResponse (

    // Fields
    long studentID,
    String name,
    Semester semester,
    Course course,
    String email
) {}
