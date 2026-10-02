package com.erick.student_api.feature.Enrollment;

import com.erick.student_api.common.dto.PageResponse;
import com.erick.student_api.common.validation.group.OnUpdate;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentPatchRequest;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentResponse;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/enrollments")
@Validated
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageResponse<EnrollmentResponse>> getAllEnrollments(
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(service.getAllEnrollments(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> getEnrollmentById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(service.getEnrollmentById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> updateEnrollmentGrade(
            @PathVariable @Positive Long id,
            @Validated(OnUpdate.class) @RequestBody EnrollmentPatchRequest request) {
        return ResponseEntity.ok(service.updateEnrollmentGrade(id, request));
    }
}
