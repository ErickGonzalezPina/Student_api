package com.erick.student_api.validation.annotation;

import com.erick.student_api.validation.validator.SchoolEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SchoolEmailValidator.class)
public @interface SchoolEmail {

    String message() default "Email must end with .edu";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}