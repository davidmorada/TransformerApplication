package com.transformers.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.annotation.*;

@Documented
@NotEmpty(message = "Transformer name must not be empty.")
@NotNull(message = "Transformer name must not be null.")
@Constraint(validatedBy = {})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidateNames {
    String message() default "Invalid Entry.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
