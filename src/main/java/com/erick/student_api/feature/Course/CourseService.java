package com.erick.student_api.feature.Course;

import com.erick.student_api.common.dto.PageResponse;
import com.erick.student_api.common.exception.CourseAlreadyExistsException;
import com.erick.student_api.common.exception.CourseNotFoundException;
import com.erick.student_api.feature.Course.dto.CourseRequest;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import com.erick.student_api.feature.Course.mapper.CourseMapper;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;



@Validated
@Service
@Transactional(readOnly = true)
public class CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    // Repository Injection
    private final CourseRepository repository;
    private final CourseMapper mapper;

    public CourseService(CourseRepository repository, CourseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // GET Logic
    public PageResponse<CourseResponse> getAllCourses(Pageable pageable) {
        log.info("Fetching all courses with pagination");

        Page<Course> coursesPage = repository.findAll(pageable);
        // Convert list of Course to CourseResponse within the Page
        Page<CourseResponse> responsePage = coursesPage.map(mapper::courseToCourseResponse);

        return new PageResponse<>(
                responsePage.getContent(),
                responsePage.getNumber(),
                responsePage.getSize(),
                responsePage.getTotalElements(),
                responsePage.getTotalPages()
        );
    }

    public CourseResponse getCourseById(@Positive Long id) {
        log.info("Fetching course with id: {}", id);
        Course course = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Course not found with id: {}", id);
                    return new CourseNotFoundException(id);
                });
        return mapper.courseToCourseResponse(course);
    }

    // POST logic
    @Transactional
    public CourseResponse addCourse(CourseRequest request) {
        log.info("Adding new course with code: {}", request.code());
        if (repository.existsByCode(request.code())) {
            log.warn("Course already exists with code: {}", request.code());
            throw new CourseAlreadyExistsException(request.code());
        }
        Course course = mapper.courseRequestToCourse(request);
        Course savedCourse = repository.save(course);
        log.info("Course added successfully with id: {}", savedCourse.getId());
        return mapper.courseToCourseResponse(savedCourse);
    }

    // UPDATE logic
    @Transactional
    public CourseResponse updateCourse(@Positive Long id, CourseRequest request) {
        log.info("Updating course with id: {}", id);
        Course course = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Course not found for update with id: {}", id);
                    return new CourseNotFoundException(id);
                });
        mapper.updateCourse(request, course);
        Course savedCourse = repository.save(course);
        log.info("Course updated successfully with id: {}", savedCourse.getId());
        return mapper.courseToCourseResponse(savedCourse);
    }

    // DELETE logic
    @Transactional
    public void deleteCourse(@Positive Long id) {
        log.info("Deleting course with id: {}", id);
        if (!repository.existsById(id)) {
            log.warn("Course not found for deletion with id: {}", id);
            throw new CourseNotFoundException(id);
        }
        repository.deleteById(id);
        log.info("Course deleted successfully with id: {}", id);
    }
}
