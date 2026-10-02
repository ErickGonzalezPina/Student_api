package com.erick.student_api.feature.Student.dto;

import com.erick.student_api.common.enums.Semester;
import com.erick.student_api.feature.Student.validation.annotation.*;
import com.erick.student_api.common.validation.group.OnUpdate;
import jakarta.validation.constraints.*;
import java.util.List;


public record StudentPatchRequest (
    // Fields
    @Size(min=1, max=100, message="Name can't be empty", groups = OnUpdate.class)
    String name,

    Semester semester,

    List<Long> courseIds,

    @Email(groups = OnUpdate.class)
    @SchoolEmail(groups = OnUpdate.class)
    String email
    ) {}