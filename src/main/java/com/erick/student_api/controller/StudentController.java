package com.erick.student_api.controller;

import com.erick.student_api.dto.*;
import com.erick.student_api.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@Validated
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
    public StudentResponse getStudentById(@PathVariable @Positive long id) {
        return service.getStudentById(id);
    }

    // POST Requests
    @PostMapping
    public StudentResponse addStudent(@Valid @RequestBody StudentRequest request) {
        return service.addStudent(request);
    }

    // PUT & PATCH Requests
    @PutMapping("/{id}")
    public StudentResponse updateStudent(
            @PathVariable @Positive long id, @Valid @RequestBody StudentRequest request) {
        return service.updateStudent(id, request);
    }

    @PatchMapping("/{id}")
    public StudentResponse updateStudentAttribute(
            @PathVariable @Positive long id, @Valid @RequestBody StudentPatchRequest request) {
        return service.updateStudentAttribute(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable @Positive long id) {
        service.deleteStudent(id);
    }
  }
