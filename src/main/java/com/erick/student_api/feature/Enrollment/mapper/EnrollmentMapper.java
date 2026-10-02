package com.erick.student_api.feature.Enrollment.mapper;

import com.erick.student_api.feature.Enrollment.Enrollment;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentPatchRequest;
import com.erick.student_api.feature.Enrollment.dto.EnrollmentResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnrollmentMapper {

    @Mapping(target = "studentId", source = "student.studentID")
    @Mapping(target = "studentName", source = "student.name")
    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseDescription", source = "course.description")
    EnrollmentResponse enrollmentToEnrollmentResponse(Enrollment enrollment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "course", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEnrollmentFromPatch(EnrollmentPatchRequest request, @MappingTarget Enrollment enrollment);
}
