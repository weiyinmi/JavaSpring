package com.accenture.web.validator;

import java.lang.annotation.Repeatable;

import javax.validation.Constraint;
import javax.validation.Payload;


@Repeatable(ListSizeLimitations.class)
@Constraint(validatedBy = ListSizeLimitationValidator.class)
public @interface ListSizeLimitation {

	String listFieldName();

	String limitedFieldName();

	String message() ;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
