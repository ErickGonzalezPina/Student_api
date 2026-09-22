package com.erick.student_api.feature.Student;

import com.erick.student_api.feature.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student>{
    // By extending JpaSpecificationExecutor we inherit findAll(Specification<Student> spec, Pageable pageable)

    boolean existsByEmail(String email);
}

