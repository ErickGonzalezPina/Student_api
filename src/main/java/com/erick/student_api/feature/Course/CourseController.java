package com.erick.student_api.feature.Course;

import com.erick.student_api.common.dto.PageResponse;
import com.erick.student_api.feature.Course.dto.CourseRequest;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import com.erick.student_api.common.validation.group.OnCreate;
import com.erick.student_api.common.validation.group.OnUpdate;
import com.erick.student_api.feature.Enrollment.EnrollmentService;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentResponse;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private final EnrollmentService enrollmentService;

    public CourseController(CourseService service, EnrollmentService enrollmentService) {
        this.service = service;
        this.enrollmentService = enrollmentService;
    }

    // GET
    @GetMapping
    public ResponseEntity<PageResponse<CourseResponse>> getAllCourses(
            @PageableDefault(size = 20, page = 0) Pageable pageable) {
        return ResponseEntity.ok(service.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(service.getCourseById(id));
    }

    @GetMapping("/{id}/enrollments")
    public ResponseEntity<PageResponse<EnrollmentResponse>> getCourseEnrollments(
            @PathVariable @Positive Long id,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(id, pageable));
    }

    // POST
    @PostMapping
    public ResponseEntity<CourseResponse> addCourse(@Validated(OnCreate.class) @RequestBody CourseRequest request) {
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
            @PathVariable @Positive Long id,
            @Validated(OnUpdate.class) @RequestBody CourseRequest request) {
        return ResponseEntity.ok(service.updateCourse(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable @Positive Long id) {
        service.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
