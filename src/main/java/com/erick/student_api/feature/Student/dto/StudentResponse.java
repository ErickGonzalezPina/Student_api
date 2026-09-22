package com.erick.student_api.feature.Student.dto;


import com.erick.student_api.common.enums.*;

public record StudentResponse (

    // Fields
    long studentID,
    String name,
    Semester semester,
    Course course,
    String email
) {}
