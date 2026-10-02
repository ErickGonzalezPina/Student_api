package com.erick.student_api.feature.Enrollment.dto;

import java.time.LocalDateTime;

public record EnrollmentResponse(
    Long id,
    Long studentId,
    String studentName,
    Long courseId,
    String courseDescription,
    LocalDateTime enrollmentDate,
    String grade
) {}
