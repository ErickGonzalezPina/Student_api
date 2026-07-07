package com.erick.student_api.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class StudentPatchRequest {

    // Fields
    @Size(min=1, max=100, message="Name can't be empty")
    private String name;

    @Pattern(regexp = "^(Spring|Summer|Fall)$", message = "Semester must be Spring, Summer, or Fall")
    private String semester;

    @Size(min=1, max=50, message="Course cant be empty")
    private String course;

    @Email
    private String email;

    // Constructor
    public StudentPatchRequest(String name, String semester, String course, String email) {
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.email = email;
    }
}
