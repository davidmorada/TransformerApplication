package com.transformers.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.*;

@Documented
@PositiveOrZero(message = "value must be positive")
@Max(value = 10, message = "value must not exceed 10")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidateScores {

    String message() default "Invalid Entry";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
