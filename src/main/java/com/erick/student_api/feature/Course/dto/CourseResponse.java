package com.erick.student_api.feature.Course.dto;

public record CourseResponse(
    Long id,
    int code,
    String description,
    int credits
) {}
