package com.erick.student_api.model;

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
    private String semester;
    @Setter
    private String course;

    // Constructor
    public Student(String name, String semester, String course) {
        this.name = name;
        this.semester = semester;
        this.course = course;
    }
}
