package com.erick.student_api.feature.Student.dto;

import com.erick.student_api.common.enums.*;

public record StudentFilter(
        Course course,
        Semester semester
) {}
