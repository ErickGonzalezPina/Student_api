package com.erick.student_api.feature.Course;

import com.erick.student_api.feature.Course.dto.CourseRequest;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import com.erick.student_api.feature.Course.mapper.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    // Repository Injection
    private final CourseRepository repository;
    private final CourseMapper mapper;

    public CourseService(CourseRepository repository, CourseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CourseResponse> getAllCourses() {
        return repository.findAll().stream()
                .map(mapper::courseToCourseResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(Long id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return mapper.courseToCourseResponse(course);
    }

    public CourseResponse addCourse(CourseRequest request) {
        Course course = mapper.courseRequestToCourse(request);
        Course savedCourse = repository.save(course);
        return mapper.courseToCourseResponse(savedCourse);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        mapper.updateCourse(request, course);
        Course savedCourse = repository.save(course);
        return mapper.courseToCourseResponse(savedCourse);
    }

    public void deleteCourse(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
