package com.erick.student_api.feature.Student.dto;

import com.erick.student_api.common.enums.*;
import com.erick.student_api.feature.Student.validation.annotation.*;
import com.erick.student_api.feature.Student.validation.group.OnUpdate;
import jakarta.validation.constraints.*;



public record StudentPatchRequest (
    // Fields
    @Size(min=1, max=100, message="Name can't be empty", groups = OnUpdate.class)
    String name,

    Semester semester,

    Course course,

    @Email(groups = OnUpdate.class)
    @SchoolEmail(groups = OnUpdate.class)
    String email
    ) {}