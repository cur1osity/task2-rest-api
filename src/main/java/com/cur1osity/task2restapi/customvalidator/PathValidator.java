package com.cur1osity.task2restapi.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomPathValidator.class)
@Target({ ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PathValidator {

     long value() default 1L;
     String message() default "Task doesn't exist !";
     Class<?>[] groups() default {};
     Class<? extends Payload>[] payload() default {};
}

