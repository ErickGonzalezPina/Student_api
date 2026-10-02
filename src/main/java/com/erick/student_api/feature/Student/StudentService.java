package com.erick.student_api.feature.Student;

import com.erick.student_api.common.dto.PageResponse;
import com.erick.student_api.feature.Course.Course;
import com.erick.student_api.feature.Course.CourseRepository;
import com.erick.student_api.feature.Student.mapper.StudentMapper;
import com.erick.student_api.feature.Student.dto.*;
import com.erick.student_api.common.exception.*;
import static com.erick.student_api.feature.Student.specification.StudentSpecification.*;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class StudentService {
    // Logger
    private static final Logger log  = LoggerFactory.getLogger(StudentService.class);

    // Repository Injection
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public StudentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentMapper = studentMapper;
    }

    // Helper Functions
    public Specification<Student> buildStudentSpecification(StudentFilter filter) {
        // (root, query, cb) -> cb.conjunction() creates a wrapper that means "WHERE 1=1" (always true)
        Specification<Student> specification = Specification.where(
                (root, query, cb) -> cb.conjunction()
        );
        // Add more conditionals "WHERE 1=1 AND ..."
        if (filter.courseId() != null) {
            specification = specification.and(hasCourse(filter.courseId()));
        }
        if (filter.semester() != null) {
            specification = specification.and(hasSemester(filter.semester()));
        }
        return specification;
    }

    private void synchronizeStudentCourses(Student student, List<Long> courseIds) {
        if (courseIds != null) {
            log.debug("Synchronizing courses for student {}", student.getStudentID());

            List<Course> courses = courseRepository.findAllById(courseIds);
            student.syncCourses(courses);
        }
    }


    // GET Requests Logic
    public PageResponse<StudentResponse> searchStudents(StudentFilter filter, Pageable pageable) {

        Specification<Student> specification = buildStudentSpecification(filter);

        Page<Student> studentsPage = studentRepository.findAll(specification, pageable);
        // convert the list of Student to StudentResponse
        Page<StudentResponse> responsePage = studentsPage.map(studentMapper::studentToStudentResponse);

        return new PageResponse<>(
                responsePage.getContent(),
                responsePage.getNumber(),
                responsePage.getSize(),
                responsePage.getTotalElements(),
                responsePage.getTotalPages()
        );
    }

    public StudentResponse getStudentById(@Positive long id) {
        log.info("Getting student by id {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student with id {} not found", id);
                    return new StudentNotFoundException(id);
                });

        log.debug("Retrieved student with id {}", id);
        return studentMapper.studentToStudentResponse(student);
    }

    // POST Request Logic
    public StudentResponse addStudent(StudentRequest request) {
        log.info("Creating new student");

        // throw error if a user already has that email
        if (studentRepository.existsByEmail(request.email())) {
            throw new StudentAlreadyExistException(request.email());
        }

        Student student = studentMapper.studentRequestToStudent(request);

        synchronizeStudentCourses(student, request.courseIds());
        Student savedStudent = studentRepository.save(student);

        log.info("Student {} created successfully", savedStudent.getStudentID());
        return studentMapper.studentToStudentResponse(savedStudent);
    }

    @Transactional
    public void enrollInCourse(@Positive long studentId, @Positive long courseId) {
        log.info("Enrolling student {} in course {}", studentId, courseId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        boolean alreadyEnrolled = student.getEnrollments().stream()
                .anyMatch(e -> e.getCourse().getId().equals(courseId));

        if (alreadyEnrolled) {
            log.warn("Student {} is already enrolled in course {}", studentId, courseId);
        }
        else {
            student.addCourse(course);
            log.info("Student {} enrolled in course {} successfully", studentId, courseId);
        }
    }

    // PUT Request Logic
    public StudentResponse updateStudent(@Positive long id, StudentRequest request) {
        log.info("Updating student with id {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot update student {} because it does not exist", id);
                    return new StudentNotFoundException(id);
                });

        studentMapper.updateStudent(request, student);

        synchronizeStudentCourses(student, request.courseIds());

        Student savedStudent = studentRepository.save(student);

        log.info("Student {} updated successfully", savedStudent.getStudentID());
        return studentMapper.studentToStudentResponse(savedStudent);
    }

    // PATCH Request Logic
    public StudentResponse updateStudentAttribute(@Positive long id, StudentPatchRequest request) {
        log.info("Updating student with id {} attributes", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot partially update student {} because it does not exist", id);
                    return new StudentNotFoundException(id);
                });

        studentMapper.updateStudentPartially(request, student);

        synchronizeStudentCourses(student, request.courseIds());

        Student savedStudent = studentRepository.save(student);

        log.info("Student with id {} partially updated successfully", id);

        return studentMapper.studentToStudentResponse(savedStudent);
    }

    // DELETE Logic
    public void deleteStudent(@Positive long id) {
        log.info("Deleting student with id {}", id);

        if (!studentRepository.existsById(id)) {
            log.warn("Student with id {} does not exist", id);
            throw new StudentNotFoundException(id);
        }
        log.info("Student {} deleted successfully", id);
        studentRepository.deleteById(id);
    }

    @Transactional
    public void unenrollFromCourse(@Positive long studentId, @Positive long courseId) {
        log.info("Unenrolling student {} from course {}", studentId, courseId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));

        student.removeCourse(course);
        studentRepository.save(student);
        log.info("Student {} unenrolled from course {} successfully", studentId, courseId);
    }
}
