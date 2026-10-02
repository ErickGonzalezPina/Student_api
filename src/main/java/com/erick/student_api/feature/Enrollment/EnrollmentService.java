package com.erick.student_api.feature.Enrollment;

import com.erick.student_api.common.dto.PageResponse;
import com.erick.student_api.common.exception.EnrollmentNotFoundException;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentPatchRequest;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentResponse;
import com.erick.student_api.feature.Enrollment.mapper.EnrollmentMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@RequiredArgsConstructor
public class EnrollmentService {

    private static final Logger log = LoggerFactory.getLogger(EnrollmentService.class);
    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;

    public PageResponse<EnrollmentResponse> getAllEnrollments(Pageable pageable) {
        log.info("Fetching all enrollments with pagination");
        Page<Enrollment> page = repository.findAll(pageable);
        
        return new PageResponse<>(
                page.getContent().stream().map(mapper::enrollmentToEnrollmentResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public EnrollmentResponse getEnrollmentById(Long id) {
        log.info("Fetching enrollment with id: {}", id);
        return repository.findById(id)
                .map(mapper::enrollmentToEnrollmentResponse)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
    }

    public EnrollmentResponse updateEnrollmentGrade(Long id, EnrollmentPatchRequest request) {
        log.info("Updating grade for enrollment id: {}", id);
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
        
        mapper.updateEnrollmentFromPatch(request, enrollment);
        Enrollment saved = repository.save(enrollment);
        
        log.info("Grade updated successfully for enrollment id: {}", id);
        return mapper.enrollmentToEnrollmentResponse(saved);
    }
}
