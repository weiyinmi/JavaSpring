package com.accenture.web.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.dto.JosephCircleRequest;
import com.accenture.web.dto.JosephCircleResponse;
import com.accenture.web.exception.BusinessException;
import com.accenture.web.service.JosephService;
import com.accenture.web.service.impl.JosephServiceImpl;

public class JosephBusinessImpl implements JosephBusiness {

	private static final String BUSINESS_EXCEPTION = "Business exception!";
	Logger logger = Logger.getLogger(JosephBusinessImpl.class);

	/**
	 * Call the Joseph function in service level,request the input data and
	 * response the result,
	 * 
	 * @param josephCircleRequest
	 *            get the input for Joseph
	 * @return responsePeople the last person
	 * @throws BusinessException
	 */
	public JosephCircleResponse callJoseph(JosephCircleRequest josephCircleRequest) throws BusinessException {

		Integer start = josephCircleRequest.getCircle().getStart();
		Integer interval = josephCircleRequest.getCircle().getInterval();
		String[] peopleArr = josephCircleRequest.getCircle().getPersons();

		List<String> peopleList = new ArrayList<>();

		for (int i = 0; i < peopleArr.length; i++) {

			peopleList.add(peopleArr[i]);
		}

		JosephService josephService = new JosephServiceImpl();
		String lastPerson = null;

		// set service exception as inner exception
		try {

			lastPerson = josephService.josephFunction(peopleList, start, interval);

		} catch (Exception e) {

			throw new BusinessException(BUSINESS_EXCEPTION, e);

		}

		JosephCircleResponse responsePeople = new JosephCircleResponse();
		responsePeople.setLastPeople(lastPerson);

		return responsePeople;

	}
}
