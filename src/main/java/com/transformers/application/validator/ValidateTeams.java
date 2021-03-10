package com.transformers.application.validator;

import com.transformers.application.exception.TransTeamCustomHandler;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {TransTeamCustomHandler.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidateTeams {
    String message() default "Invalid Entry. Enter (A) for Autobots or (D) for Decepticons.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
