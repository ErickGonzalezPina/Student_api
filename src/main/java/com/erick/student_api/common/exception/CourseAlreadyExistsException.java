package com.erick.student_api.common.exception;

public class CourseAlreadyExistsException extends RuntimeException {
    public CourseAlreadyExistsException(int code) {
        super("Course with code " + code + " already exists");
    }
}
