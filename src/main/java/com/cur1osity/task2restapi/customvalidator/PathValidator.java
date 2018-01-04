package com.cur1osity.task2restapi.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CustomPathValidator.class)
@Target({ ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PathValidator {

    public long value() default 1L;

    public String message() default "Task doesn't exist !";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}

