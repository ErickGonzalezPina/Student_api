package com.erick.student_api.exception;


public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(long id) {
        super("Student with ID " + id + " not found");
    }
}
