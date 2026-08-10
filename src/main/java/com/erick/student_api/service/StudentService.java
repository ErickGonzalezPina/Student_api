package com.erick.student_api.service;

import com.erick.student_api.repository.StudentRepository;
import com.erick.student_api.dto.*;
import com.erick.student_api.exception.*;
import com.erick.student_api.model.Student;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;


@Validated
@Service
public class StudentService {

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
        return studentRepository.findAll().stream().map(this::mapToStudentResponse).toList();
    }

    public StudentResponse getStudentById(@Positive long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return mapToStudentResponse(student);
    }

    // POST Request Logic
    public StudentResponse addStudent(@Valid StudentRequest request) {
        Student savedStudent = studentRepository.save(mapToStudent(request));
        return mapToStudentResponse(savedStudent);
    }

    // PUT Request Logic
    public StudentResponse updateStudent(@Positive long id, @Valid StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        student.setName(request.name());
        student.setCourse(request.course());
        student.setSemester(request.semester());
        student.setEmail(request.email());

        studentRepository.save(student);
        return mapToStudentResponse(student);
    }

    // PATCH Request Logic
    public StudentResponse updateStudentAttribute(@Positive long id, @Valid StudentPatchRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

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
        return mapToStudentResponse(student);
    }

    // DELETE Logic
    public void deleteStudent(@Positive long id) {
        studentRepository.deleteById(id);
    }
}
