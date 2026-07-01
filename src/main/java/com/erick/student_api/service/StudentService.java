package com.erick.student_api.service;

import com.erick.student_api.repository.StudentRepository;
import com.erick.student_api.dto.*;
import com.erick.student_api.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                student.getCourse());
    }
    public Student mapToStudent(StudentRequest request) {
        return new Student(
                request.getName(),
                request.getSemester(),
                request.getCourse()
        );
    }

    // GET Requests Logic
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponse> response = new ArrayList<>();

        for (Student student: students) {
            response.add(mapToStudentResponse(student));
        }
        return response;
    }

    public StudentResponse getStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToStudentResponse(student);
    }

    // POST Request Logic
    public StudentResponse addStudent(StudentRequest request) {
        Student savedStudent = studentRepository.save(mapToStudent(request));
        return mapToStudentResponse(savedStudent);
    }

    // PUT Request Logic
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(request.getName());
        student.setCourse(request.getCourse());
        student.setSemester(request.getSemester());

        studentRepository.save(student);
        return mapToStudentResponse(student);
    }

    // PATCH Request Logic
    public StudentResponse updateStudentAttribute(Long id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (request.getName() != null) {
            student.setName(request.getName());
        }
        else if (request.getCourse() != null) {
            student.setCourse(request.getCourse());
        }
        else if (request.getSemester() != null) {
            student.setSemester(request.getSemester());
        }
        studentRepository.save(student);
        return mapToStudentResponse(student);
    }

    // DELETE Logic
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
