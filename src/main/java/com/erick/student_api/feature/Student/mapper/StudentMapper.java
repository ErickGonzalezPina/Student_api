package com.erick.student_api.feature.Student.mapper;

import com.erick.student_api.feature.Student.dto.*;
import com.erick.student_api.feature.Student.Student;
import org.mapstruct.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StudentMapper {

    StudentResponse studentToStudentResponse(Student student);

    Student studentRequestToStudent(StudentRequest studentRequest);

    void updateStudent(StudentRequest studentRequest, @MappingTarget Student student);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateStudentPartially(StudentPatchRequest studentRequest, @MappingTarget Student student);

}
