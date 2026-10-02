package com.erick.student_api.feature.Course;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@NoArgsConstructor
public class Course {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int code;

    @Setter
    private String description;

    @Setter
    private int credits;

    // Constructor
    public Course(int code, String description, int credits) {
        this.code = code;
        this.description = description;
        this.credits = credits;
    }
}
