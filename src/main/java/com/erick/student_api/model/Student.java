package com.erick.student_api.model;

import com.erick.student_api.enums.Semester;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    @Setter
    private String name;
    @Setter
    @Enumerated(EnumType.STRING)
    private Semester semester;
    @Setter
    private String course;
    @Setter
    private String email;

    // Constructor
    public Student(String name, Semester semester, String course, String email) {
        this.name = name;
        this.semester = semester;
        this.course = course;
        this.email = email;
    }
}
