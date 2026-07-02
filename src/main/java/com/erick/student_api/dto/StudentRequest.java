package com.erick.student_api.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentRequest {
    // Fields
    @NotBlank(message = "Need name")
    private String name;
    @NotBlank
    private String semester;
    @NotBlank
    private String course;
    @NotBlank
    @Email
    private String email;

    // Constructor
    public StudentRequest(String name, String semester, String course, String email) {
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.email = email;
    }
}
