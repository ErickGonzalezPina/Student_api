package com.erick.student_api.feature.Student.validation.validator;

import com.erick.student_api.feature.Student.validation.annotation.SchoolEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SchoolEmailValidator implements ConstraintValidator<SchoolEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }
        return value.endsWith(".edu");
    }
}
