package com.erick.student_api.dto;



public record StudentResponse (

    // Fields
    long studentID,
    String name,
    String semester,
    String course,
    String email
) {}
