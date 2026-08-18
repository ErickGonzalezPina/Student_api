package com.erick.student_api.controller;

import com.erick.student_api.dto.*;
import com.erick.student_api.service.StudentService;

import com.erick.student_api.validation.group.OnCreate;
import com.erick.student_api.validation.group.OnUpdate;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable @Positive long id) {
        return ResponseEntity.ok(service.getStudentById(id));
    }

    // POST Requests
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(
            @Validated(OnCreate.class) @RequestBody StudentRequest request) {

        StudentResponse student = service.addStudent(request);
        URI location = URI.create("/api/v1/students/" + student.studentID());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", location.toString())
                .body(student);
    }

    // PUT & PATCH Requests
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(
            @PathVariable @Positive long id,
            @Validated(OnUpdate.class) @RequestBody StudentRequest request) {

        return ResponseEntity.ok(service.updateStudent(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudentAttribute(
            @PathVariable @Positive long id,
            @Validated(OnUpdate.class) @RequestBody StudentPatchRequest request) {

        return ResponseEntity.ok(service.updateStudentAttribute(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable @Positive long id) {

        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
  }
