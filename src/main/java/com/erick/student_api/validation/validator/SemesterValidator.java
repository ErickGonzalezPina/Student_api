package com.erick.student_api.validation.validator;

import com.erick.student_api.validation.annotation.ValidSemester;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class SemesterValidator implements ConstraintValidator<ValidSemester, String> {
    private static final Set<String> VALID_SEMESTERS = Set.of("Spring", "Summer", "Fall");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return false;
        }
        return VALID_SEMESTERS.contains(value);
    }
}
