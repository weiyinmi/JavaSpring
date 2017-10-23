package com.accenture.web.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD,TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListSizeLimitations {
	
	ListSizeLimitation[] value();
}
