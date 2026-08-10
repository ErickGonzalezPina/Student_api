package com.erick.student_api.dto;

import com.erick.student_api.validation.annotation.SchoolEmail;
import com.erick.student_api.validation.annotation.ValidSemester;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentRequest {

    // Fields
    @NotBlank
    private String name;

    @ValidSemester
    private String semester;

    @NotBlank
    private String course;

    @Email
    @SchoolEmail
    private String email;

    // Constructor
    public StudentRequest(String name, String semester, String course, String email) {
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.email = email;
    }
}
