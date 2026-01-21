package com.example.springweb.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRole==null) {
            return true;
        }
        List<String> roles= List.of("ADMIN","USER");
        return roles.contains(inputRole);
    }
}
