package com.accenture.web.validator;

import java.util.HashSet;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoRepetitionListValidator implements ConstraintValidator<NoRepetitionList, List<String>> {

	@Override
	public void initialize(NoRepetitionList arg0) {

	}

	@Override
	public boolean isValid(List<String> personList, ConstraintValidatorContext arg1) {
		
		HashSet<String> hashset = new HashSet<>();		
		if (personList != null) {
			
			for (String i : personList) {
				hashset.add(i);
			}
			if (hashset.size() == personList.size()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

}
