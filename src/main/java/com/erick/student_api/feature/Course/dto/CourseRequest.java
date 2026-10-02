package com.erick.student_api.feature.Course.dto;

public record CourseRequest(
    int code,
    String description,
    int credits
) {}
