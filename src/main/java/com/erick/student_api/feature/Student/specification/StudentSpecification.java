package com.erick.student_api.feature.Student.specification;

import com.erick.student_api.feature.Enrollment.Enrollment;
import com.erick.student_api.common.enums.Semester;
import com.erick.student_api.feature.Student.Student;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpecification {

    public static Specification<Student> hasCourse(Long courseId) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            Join<Student, Enrollment> enrollmentJoin = root.join("enrollments");
            return criteriaBuilder.equal(enrollmentJoin.get("course").get("id"), courseId);
        };
    }

    public static Specification<Student> hasSemester(Semester semester) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("semester"), semester);
    }
}
