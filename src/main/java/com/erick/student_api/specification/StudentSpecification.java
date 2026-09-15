package com.erick.student_api.specification;

import com.erick.student_api.enums.Semester;
import com.erick.student_api.model.Student;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpecification {

    public static Specification<Student> hasCourse(String course) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("course"), course);
    }

    public static Specification<Student> hasSemester(Semester semester) {
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.equal(root.get("semester"), semester);
    }
}
