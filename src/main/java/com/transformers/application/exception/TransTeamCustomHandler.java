package com.transformers.application.exception;

import com.transformers.application.validator.ValidateTeams;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransTeamCustomHandler implements ConstraintValidator<ValidateTeams,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return (value.equals("A")) || (value.equals("D")) ;
    }
}
