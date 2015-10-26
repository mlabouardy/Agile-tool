package com.bordeaux.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.bordeaux.validator.UniqueEmailValidator;


@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = { UniqueEmailValidator.class})
public @interface UniqueEmail {
	String message();

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
