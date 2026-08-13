package com.erick.student_api.dto;


import com.erick.student_api.enums.Semester;
import com.erick.student_api.validation.annotation.*;
import jakarta.validation.constraints.*;



public record StudentPatchRequest (
    // Fields
    @Size(min=1, max=100, message="Name can't be empty")
    String name,


    Semester semester,

    @Size(min=1, max=50, message="Course cant be empty")
    String course,

    @Email
    @SchoolEmail
    String email
    ) {}