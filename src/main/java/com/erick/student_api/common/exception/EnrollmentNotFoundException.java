package com.erick.student_api.common.exception;

public class EnrollmentNotFoundException extends RuntimeException {
    public EnrollmentNotFoundException(Long id) {
        super("Enrollment with ID " + id + " not found");
    }
}
