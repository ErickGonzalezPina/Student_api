package com.erick.student_api.feature.Student.dto;

import com.erick.student_api.common.enums.*;
import com.erick.student_api.feature.Student.validation.annotation.SchoolEmail;
import com.erick.student_api.feature.Student.validation.group.*;
import jakarta.validation.constraints.*;


public record StudentRequest (

    // Fields
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String name,

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    Semester semester,

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    Course course,

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @SchoolEmail(groups = {OnCreate.class, OnUpdate.class})
     String email
) {}
