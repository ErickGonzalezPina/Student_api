package com.erick.student_api.feature.Course.mapper;

import com.erick.student_api.feature.Course.Course;
import com.erick.student_api.feature.Course.dto.CourseRequest;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CourseMapper {

    CourseResponse courseToCourseResponse(Course course);

    Course courseRequestToCourse(CourseRequest courseRequest);

    void updateCourse(CourseRequest courseRequest, @MappingTarget Course course);
}
