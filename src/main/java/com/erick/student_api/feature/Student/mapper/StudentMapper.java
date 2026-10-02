package com.erick.student_api.feature.Student.mapper;

import com.erick.student_api.feature.Course.Course;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import com.erick.student_api.feature.Course.mapper.CourseMapper;
import com.erick.student_api.feature.Enrollment.Enrollment;
import com.erick.student_api.feature.Student.dto.*;
import com.erick.student_api.feature.Student.Student;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {CourseMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    @Mapping(target = "courses", expression = "java(mapEnrollmentsToCourses(student.getEnrollments()))")
    StudentResponse studentToStudentResponse(Student student);

    default List<CourseResponse> mapEnrollmentsToCourses(Set<Enrollment> enrollments) {
        if (enrollments == null) return null;
        return enrollments.stream()
                .map(enrollment -> {
                    Course course = enrollment.getCourse();
                    return new CourseResponse(
                            course.getId(),
                            course.getCode(),
                            course.getDescription(),
                            course.getCredits()
                    );
                })
                .collect(Collectors.toList());
    }

    @Mapping(target = "enrollments", ignore = true)
    Student studentRequestToStudent(StudentRequest studentRequest);

    @Mapping(target = "enrollments", ignore = true)
    void updateStudent(StudentRequest studentRequest, @MappingTarget Student student);

    @Mapping(target = "enrollments", ignore = true)
    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateStudentPartially(StudentPatchRequest studentRequest, @MappingTarget Student student);

}
