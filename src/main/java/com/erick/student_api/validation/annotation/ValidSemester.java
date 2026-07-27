package com.erick.student_api.validation.annotation;

import com.erick.student_api.validation.validator.SemesterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SemesterValidator.class)
public @interface ValidSemester {
    String message() default "Semester must be Spring, Summer, or Fall";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
