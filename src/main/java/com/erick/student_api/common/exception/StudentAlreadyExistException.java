package com.erick.student_api.common.exception;


public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(String email) {
        super("User with email " + email + " already exists");
    }
}
