package com.erick.student_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentRequest {
    // Fields
    private String name;
    private String semester;
    private String course;

    // Constructor
    public StudentRequest(String name, String semester, String course) {
        this.name = name;
        this.semester = semester;
        this.course = course;
    }
}
