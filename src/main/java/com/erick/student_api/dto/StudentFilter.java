package com.erick.student_api.dto;

import com.erick.student_api.enums.Semester;

public record StudentFilter(
        String course,
        Semester semester
) {}
