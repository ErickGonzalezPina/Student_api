package com.erick.student_api.feature.Course;


import com.erick.student_api.feature.Enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();

    // Constructor
    public Course(int code, String description, int credits) {
        this.code = code;
        this.description = description;
        this.credits = credits;
    }
}
