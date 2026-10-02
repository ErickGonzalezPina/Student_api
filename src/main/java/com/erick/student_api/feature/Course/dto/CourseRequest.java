package com.erick.student_api.feature.Course.dto;

import com.erick.student_api.common.validation.group.OnCreate;
import com.erick.student_api.common.validation.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CourseRequest(
    @Positive(groups = {OnCreate.class, OnUpdate.class})
    int code,

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    String description,

    @Positive(groups = {OnCreate.class, OnUpdate.class})
    int credits
) {}
