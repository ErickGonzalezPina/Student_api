package com.erick.student_api.feature.Enrollment.dto;

import com.erick.student_api.common.validation.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnrollmentPatchRequest(
    @NotBlank(groups = OnUpdate.class)
    @Pattern(regexp = "^[A-F][+-]?|N/A$", message = "Invalid grade format", groups = OnUpdate.class)
    String grade
) {}
