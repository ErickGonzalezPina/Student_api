package com.erick.student_api.dto;

import com.erick.student_api.enums.Semester;
import com.erick.student_api.validation.annotation.SchoolEmail;
import com.erick.student_api.validation.group.OnCreate;
import com.erick.student_api.validation.group.OnUpdate;
import jakarta.validation.constraints.*;


public record StudentRequest (

    // Fields
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String name,

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    Semester semester,

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String course,

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Email(groups = {OnCreate.class, OnUpdate.class})
    @SchoolEmail(groups = {OnCreate.class, OnUpdate.class})
     String email
) {}
