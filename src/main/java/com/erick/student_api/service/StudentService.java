package com.erick.student_api.service;

import com.erick.student_api.mapper.StudentMapper;
import com.erick.student_api.repository.StudentRepository;
import com.erick.student_api.dto.*;
import com.erick.student_api.exception.*;
import com.erick.student_api.model.Student;
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
    private final StudentMapper studentMapper;

    public StudentService(
            StudentRepository studentRepository,
            StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    // GET Requests Logic
    public List<StudentResponse> getAllStudents() {
        log.info("Getting all students");

        List<StudentResponse> students = studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentResponse)
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
        return studentMapper.studentToStudentResponse(student);
    }

    // POST Request Logic
    public StudentResponse addStudent(StudentRequest request) {
        log.info("Creating new student");

        Student student = studentMapper.studentRequestToStudent(request);
        Student savedStudent = studentRepository.save(student);

        log.info("Student {} created successfully", savedStudent.getStudentID());
        return studentMapper.studentToStudentResponse(savedStudent);
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
}
