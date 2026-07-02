package com.erick.student_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class StudentResponse {
    // Fields
    private long studentID;
    private String name;
    private String semester;
    private String course;
    private String email;

    // Constructor
    public StudentResponse(long studentID, String name, String semester, String course, String email) {
        this.studentID = studentID;
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.email = email;
    }
}
