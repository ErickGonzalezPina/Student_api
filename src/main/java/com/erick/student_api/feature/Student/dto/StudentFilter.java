package com.erick.student_api.feature.Student.dto;

import com.erick.student_api.common.enums.Semester;

public record StudentFilter(
        Long courseId,
        Semester semester
) {}
