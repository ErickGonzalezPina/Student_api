package com.erick.student_api.feature.Student.dto;


import com.erick.student_api.common.enums.Semester;
import com.erick.student_api.feature.Course.dto.CourseResponse;

import java.util.List;

public record StudentResponse (

    // Fields
    long studentID,
    String name,
    Semester semester,
    List<CourseResponse> courses,
    String email
) {}
