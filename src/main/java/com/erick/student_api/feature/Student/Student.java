package com.erick.student_api.feature.Student;

import com.erick.student_api.common.enums.Semester;
import com.erick.student_api.feature.Course.Course;
import com.erick.student_api.feature.Enrollment.Enrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;


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
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollments = new HashSet<>();

    // Constructor
    public Student(String name, Semester semester, String email) {
        this.name = name;
        this.semester = semester;
        this.email = email;
    }

    // Helper methods for synchronization
    public void addCourse(Course course) {
        Enrollment enrollment = new Enrollment(this, course); // Create enrollment
        this.enrollments.add(enrollment); // link enrollment to student
        course.getEnrollments().add(enrollment); // link enrollment to course
    }

    public void removeCourse(Course course) {
        this.enrollments.removeIf(enrollment -> {
            if (enrollment.getCourse().equals(course)) {
                enrollment.getCourse().getEnrollments().remove(enrollment);
                return true;
            }
            return false;
        });
    }

    public void clearCourses() {
        this.enrollments.forEach(enrollment -> enrollment.getCourse().getEnrollments().remove(enrollment));
        this.enrollments.clear();
    }
}
