package com.accenture.web.business.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.dto.JosephRequest;
import com.accenture.web.dto.JosephResponse;
import com.accenture.web.exception.BusinessException;
import com.accenture.web.service.JosephService;

public class JosephBusinessImpl implements JosephBusiness {

	private static final String BUSINESS_EXCEPTION = "Business exception!";
	Logger logger = Logger.getLogger(JosephBusinessImpl.class);

	private JosephService josephService;

	public JosephService getJosephService() {
		return josephService;
	}

	public void setJosephService(JosephService josephService) {
		this.josephService = josephService;
	}

	/**
	 * Call the Joseph function in service level,request the input data and
	 * response the result,
	 * 
	 * @param josephRequest
	 *            get the input for Joseph
	 * @return responsePeople the last person
	 * @throws BusinessException
	 */
	public JosephResponse callJoseph(JosephRequest josephRequest) throws BusinessException {

		Integer start = josephRequest.getCircle().getStart();
		Integer interval = josephRequest.getCircle().getInterval();
		List<String> peopleList = josephRequest.getCircle().getPersons();

		String lastPerson = null;

		try {

			lastPerson = josephService.josephFunction(peopleList, start, interval);

		} catch (Exception e) {

			throw new BusinessException(BUSINESS_EXCEPTION, e);

		}

		JosephResponse responsePeople = new JosephResponse();
		responsePeople.setLastPeople(lastPerson);

		return responsePeople;

	}
}
