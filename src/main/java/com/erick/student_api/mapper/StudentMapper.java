package com.erick.student_api.mapper;

import com.erick.student_api.dto.*;
import com.erick.student_api.model.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponse studentToStudentResponse(Student student);

    Student studentRequestToStudent(StudentRequest studentRequest);

    void updateStudent(StudentRequest studentRequest, @MappingTarget Student student);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    void updateStudentPartially(StudentPatchRequest studentRequest, @MappingTarget Student student);

}
