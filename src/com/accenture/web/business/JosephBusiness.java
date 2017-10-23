package com.accenture.web.business;

import com.accenture.web.dto.JosephRequest;
import com.accenture.web.dto.JosephResponse;
import com.accenture.web.exception.BusinessException;

public interface JosephBusiness {
	public JosephResponse callJoseph(JosephRequest josephRequest) throws BusinessException;
}

