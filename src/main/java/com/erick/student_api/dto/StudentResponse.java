package com.erick.student_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    // Fields
    private Long studentID;
    private String name;
    private String semester;
    private String course;

    // Constructor
    public StudentResponse(Long studentID, String name, String semester, String course) {
        this.studentID = studentID;
        this.name = name;
        this.semester = semester;
        this.course = course;
    }
}
