package com.erick.student_api.service;

import com.erick.student_api.repository.StudentRepository;
import com.erick.student_api.dto.*;
import com.erick.student_api.exception.*;
import com.erick.student_api.model.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;


@Validated
@Service
public class StudentService {
    // Logger
    private static final Logger log  = LoggerFactory.getLogger(StudentService.class);

    // Repository Injection
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Helper Functions
    public StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getStudentID(),
                student.getName(),
                student.getSemester(),
                student.getCourse(),
                student.getEmail()
        );
    }
    public Student mapToStudent(StudentRequest request) {
        return new Student(
                request.name(),
                request.semester(),
                request.course(),
                request.email()
        );
    }

    // GET Requests Logic
    public List<StudentResponse> getAllStudents() {
        log.info("Getting all students");

        List<StudentResponse> students = studentRepository.findAll()
                .stream()
                .map(this::mapToStudentResponse)
                .toList();

        log.debug("Retrieved {} students", students.size());
        return students;
    }

    public StudentResponse getStudentById(@Positive long id) {
        log.info("Getting student by id {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student with id {} not found", id);
                    return new StudentNotFoundException(id);
                });

        log.debug("Retrieved student with id {}", id);
        return mapToStudentResponse(student);
    }

    // POST Request Logic
    public StudentResponse addStudent(@Valid StudentRequest request) {
        log.info("Creating new student");
        Student savedStudent = studentRepository.save(mapToStudent(request));
        log.info("Student {} created successfully", savedStudent.getStudentID());
        return mapToStudentResponse(savedStudent);
    }

    // PUT Request Logic
    public StudentResponse updateStudent(@Positive long id, @Valid StudentRequest request) {
        log.info("Updating student with id {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot update student {} because it does not exist", id);
                    return new StudentNotFoundException(id);
                });

        student.setName(request.name());
        student.setCourse(request.course());
        student.setSemester(request.semester());
        student.setEmail(request.email());

        studentRepository.save(student);
        log.info("Student {} updated successfully", student.getStudentID());
        return mapToStudentResponse(student);
    }

    // PATCH Request Logic
    public StudentResponse updateStudentAttribute(@Positive long id, @Valid StudentPatchRequest request) {
        log.info("Updating student with id {} attributes", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cannot partially update student {} because it does not exist", id);
                    return new StudentNotFoundException(id);
                });

        if (request.name() != null) {
            student.setName(request.name());
        }
        if (request.course() != null) {
            student.setCourse(request.course());
        }
        if (request.semester() != null) {
            student.setSemester(request.semester());
        }
        if (request.email() != null) {
            student.setEmail(request.email());
        }
        studentRepository.save(student);
        log.info("Student with id {} partially updated successfully", id);
        return mapToStudentResponse(student);
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
}
