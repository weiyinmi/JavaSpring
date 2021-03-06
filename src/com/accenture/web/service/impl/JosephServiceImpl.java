package com.accenture.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.accenture.web.service.JosephService;

@Repository
public class JosephServiceImpl implements JosephService {
	private static final String START_EXCEPTION = "Start isn`t a positive integer!";
	private static final String INTERVAL_EXCEPTION = "Interval isn`t a non-negative integer!";
	private static final String ILLEGAL_INPUT = "Illegal input!";
	Logger logger = Logger.getLogger(JosephServiceImpl.class);

	/**
	 * Validate the Joseph input,list cann`t be null,start must be a positive
	 * integer and interval must be a non-negative integer,or throw exception.
	 * 
	 * @param list
	 *            input several peoples as a list
	 * @param start
	 *            start index
	 * @param interval
	 *            interval
	 * @return true or throw exception
	 * @throws NumberFormatException
	 */
	/*
	 * public boolean validateInput(List<String> list, String start, String
	 * interval) {
	 * 
	 * int intStart; int intInterval; try { intStart = Integer.parseInt(start);
	 * intInterval = Integer.parseInt(interval);
	 * 
	 * } catch (NumberFormatException e) { throw new
	 * IllegalArgumentException(ILLEGAL_INPUT, e); } if (intStart > 0) { if
	 * (intInterval >= 0) { return true; } else { throw new
	 * IllegalArgumentException(INTERVAL_EXCEPTION); } } else { throw new
	 * IllegalArgumentException(START_EXCEPTION); } }
	 */

	public boolean validateInput(List<String> list, Integer start, Integer interval) {
		
		if (list == null) {
			throw new NullPointerException("null");
		}
		
		if (start > 0) {
			if (interval >= 0) {
				return true;
			} else {
				throw new IllegalArgumentException(INTERVAL_EXCEPTION);
			}
		} else {
			throw new IllegalArgumentException(START_EXCEPTION);
		}

	}

	/**
	 * Achieve joseph problem,when josephFunction is working, peoples would be
	 * removed one by one,until leave the last one.
	 * 
	 * @param list
	 *            input several peoples as a list
	 * @param start
	 *            start index
	 * @param interval
	 *            interval
	 * @return the last person
	 * @throws IllegalArgumentException
	 */
	public String josephFunction(List<String> list, Integer start, Integer interval) {

		if (validateInput(list, start, interval)) {
			int a = start - 1; // index
			while (list.size() > 1) {
				a = a + interval;
				a = a % (list.size());
				if (a < 0) {
					list.remove(list.size() - 1);
					a = 0;
				} else {
					list.remove(a);
				}
			}
		}
		return list.get(0);
	}
}
