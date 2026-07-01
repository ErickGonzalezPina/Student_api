package com.erick.student_api.controller;

import com.erick.student_api.dto.StudentResponse;
import com.erick.student_api.model.Student;
import com.erick.student_api.dto.*;
import com.erick.student_api.service.StudentService;

import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    // Service Injection
    private final StudentService service;
    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET Requests
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable long id) {
        return service.getStudentById(id);
    }

    // POST Requests
    @PostMapping
    public StudentResponse addStudent(@RequestBody StudentRequest request) {
        return service.addStudent(request);
    }

    // PUT & PATCH Requests
    @PutMapping("/{id}")
    public StudentResponse updateStudent(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.updateStudent(id, request);
    }

    @PatchMapping("/{id}")
    public StudentResponse updateStudentAttribute(@PathVariable Long id, @RequestBody StudentRequest request) {
        return service.updateStudentAttribute(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }


  }
