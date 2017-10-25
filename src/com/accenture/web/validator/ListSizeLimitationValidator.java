package com.accenture.web.validator;

import java.lang.reflect.Field;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.log4j.Logger;

import com.accenture.web.aop.PrintTime;

public class ListSizeLimitationValidator implements ConstraintValidator<ListSizeLimitation, Object> {

	private static final Logger logger = Logger.getLogger(PrintTime.class);

	private String limitedFieldName;
	private String listFieldName;

	@Override
	public void initialize(ListSizeLimitation listsize) {
		limitedFieldName = listsize.limitedFieldName();
		listFieldName = listsize.listFieldName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {

		Integer fieldLength = 0;
		int maxSize = 0;

		Field[] fields = object.getClass().getDeclaredFields();

		for (Field field : fields) {

			field.setAccessible(true);

			try {

				if (field.getName().indexOf(limitedFieldName) != -1) {

					fieldLength = (Integer) field.get(object);
				}

				if (field.getName().indexOf(listFieldName) != -1) {
					List<String> listPeo;
					listPeo = (List<String>) field.get(object);

					if (listPeo != null) {
						maxSize = listPeo.size();
					}
				}

			} catch (IllegalArgumentException | IllegalAccessException e) {

				logger.warn("Circle validate error:", e);
			} catch (NullPointerException e) {
				logger.warn("Field cann't be null!", e);
			}
		}

		if (fieldLength == null) {
			return true;
		}

		if (fieldLength > maxSize) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{data.size.limitation}").addPropertyNode(limitedFieldName)
					.addConstraintViolation();

			return false;
		} else {

			return true;
		}
	}
}