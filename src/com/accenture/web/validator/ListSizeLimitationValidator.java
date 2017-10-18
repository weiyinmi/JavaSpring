package com.accenture.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ListSizeLimitationValidator implements ConstraintValidator<ListSizeLimitation, Integer> {

	private int maxSize;
	
	@Override
	public void initialize(ListSizeLimitation listSize) {
		maxSize = 4;
	}

	@Override
	public boolean isValid(Integer Integer, ConstraintValidatorContext context) {	
		
		return Integer <= maxSize;
	}
}
