package com.accenture.web.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;

import com.accenture.web.aop.PrintTime;

public class ListSizeLimitationValidator implements ConstraintValidator<ListSizeLimitation, Object> {

	private static final Logger logger = Logger.getLogger(PrintTime.class);
	
	private List<Integer> length;
	private int maxSize;

	@Override
	public void initialize(ListSizeLimitation listsize) {
		listsize.limitedFieldName();
		listsize.listFieldName();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {

		Field[] fields = object.getClass().getDeclaredFields();
		List<String> fieldList = new ArrayList<>();
		length = new ArrayList<>();

		for (Field field : fields) {

			field.setAccessible(true);
			fieldList.add(field.getName());

			try {
				if (Integer.class.isAssignableFrom(field.getType())) {

					length.add((Integer) field.get(object));
				}

				if (List.class.isAssignableFrom(field.getType())) {
					List<String> listPeo = new ArrayList<>();
					listPeo = (List<String>) field.get(object);
					maxSize = listPeo.size();

				}
			} catch (IllegalArgumentException | IllegalAccessException e) {

				logger.warn("Circle validate error:",e);
			}
		}

		if (length.get(0) > maxSize) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{start.validator.message}").addConstraintViolation();

			return false;

		} else if (length.get(1) > maxSize) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{interval.validator.message}").addConstraintViolation();

			return false;

		} else {
			return true;
		}
	}

}