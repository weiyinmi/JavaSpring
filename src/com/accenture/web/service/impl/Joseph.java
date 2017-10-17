package com.accenture.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.web.exception.BusinessException;

public class Joseph {

	Logger logger = Logger.getLogger(Joseph.class);

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
	 */
	public String josephFunction(List<String> list, int start, int interval) throws BusinessException {

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
			return list.get(0);
		
	}

}
