package com.github.malipio.eniro.search.domain.validator;

import java.lang.annotation.*;

import javax.validation.Constraint;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=RegExpPatternValidator.class)
public @interface RegExpPattern {
	String message() default "Invalid regular expression pattern";
	Class<?>[] groups() default {};
	Class<?>[] payload() default {};
}
