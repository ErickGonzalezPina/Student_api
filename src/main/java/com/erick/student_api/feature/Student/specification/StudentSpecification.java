package com.erick.student_api.feature.Student.specification;

import com.erick.student_api.common.enums.*;
import com.erick.student_api.feature.Student.Student;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpecification {

    public static Specification<Student> hasCourse(Course course) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("course"), course);
    }

    public static Specification<Student> hasSemester(Semester semester) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("semester"), semester);
    }
}
