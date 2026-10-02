package com.erick.student_api.feature.Student.mapper;

import com.erick.student_api.feature.Course.Course;
import com.erick.student_api.feature.Course.dto.CourseResponse;
import com.erick.student_api.feature.Course.mapper.CourseMapper;
import com.erick.student_api.feature.Enrollment.Enrollment;
import com.erick.student_api.feature.Student.dto.*;
import com.erick.student_api.feature.Student.Student;
import org.mapstruct.*;



@Mapper(componentModel = "spring", uses = {CourseMapper.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    @Mapping(target = "courses", source = "enrollments")
    StudentResponse studentToStudentResponse(Student student);

    @Mapping(target = ".", source = "course")
    CourseResponse enrollmentToCourseResponse(Enrollment enrollment);

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
