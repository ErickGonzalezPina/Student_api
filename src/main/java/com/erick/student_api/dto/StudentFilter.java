package com.erick.student_api.dto;

import com.erick.student_api.enums.Course;
import com.erick.student_api.enums.Semester;

public record StudentFilter(
        Course course,
        Semester semester
) {}
