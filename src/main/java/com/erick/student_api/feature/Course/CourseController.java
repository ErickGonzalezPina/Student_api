package com.erick.student_api.feature.Course;

import com.erick.student_api.feature.Course.dto.CourseRequest;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
@Validated
public class CourseController {

    // Service injection
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    // POST
    @PostMapping
    public ResponseEntity<CourseResponse> addCourse(@RequestBody CourseRequest request) {
        CourseResponse course = service.addCourse(request);
        URI location = URI.create("/api/v1/courses/" + course.id());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", location.toString())
                .body(course);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequest request) {
        return ResponseEntity.ok(service.updateCourse(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
