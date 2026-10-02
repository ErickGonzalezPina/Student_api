package com.erick.student_api.common.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(long id) {
        super("Course with ID " + id + " not found");
    }
}
