package com.erick.student_api.dto;

import com.erick.student_api.validation.annotation.SchoolEmail;
import jakarta.validation.constraints.*;


public record StudentRequest (

    // Fields
    @NotBlank
    String name,


    String semester,

    @NotBlank
    String course,

    @Email
    @SchoolEmail
     String email
) {}
