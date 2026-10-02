package com.erick.student_api.feature.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // JpaRepository supports offset pagination findAll(Pageable)

    boolean existsByCode(int code);
}
