package com.erick.student_api.exception;


public class StudentAlreadyExistException extends RuntimeException{
    public StudentAlreadyExistException(String email) {
        super("User with email " + email + " already exists");
    }
}
