package com.accenture.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.business.impl.JosephBusinessImpl;
import com.accenture.web.dto.JosephCircleRequest;
import com.accenture.web.dto.JosephCircleResponse;
import com.accenture.web.dto.JosephErrorMessage;

@Controller
public class JosephController {

	private static final Logger logger = Logger.getLogger(JosephController.class);

	JosephBusiness josephBusiness = new JosephBusinessImpl();
	JosephCircleResponse josephCircleResponse = null;

	@RequestMapping("/JosephController")
	public @ResponseBody JosephCircleResponse getLastPeople(@Valid @RequestBody JosephCircleRequest josephCircleRequest,
			BindingResult result) {

		if (result.hasErrors()) {

			logger.error("Inputs are illegal!");

			List<FieldError> errorList = result.getFieldErrors();			
			List<JosephErrorMessage> stringErr = new ArrayList<>();

			for (FieldError fieldError : errorList) {
				JosephErrorMessage josephErrorMessage = new JosephErrorMessage();
				josephErrorMessage.setField(fieldError.getField());
				josephErrorMessage.setMessage(fieldError.getDefaultMessage());
				stringErr.add(josephErrorMessage);
				
				logger.error("Error: " + fieldError.getField() + " " + fieldError.getDefaultMessage());
			}
	
			josephCircleResponse = new JosephCircleResponse();
			josephCircleResponse.setErrors(stringErr); // must new a object, or NullPointer														
			josephCircleResponse.setLastPeople(null);

			return josephCircleResponse;
		}

		try {

			josephCircleResponse = josephBusiness.callJoseph(josephCircleRequest);
			logger.info("Succeed  to run Joseph");

		} catch (Exception e) {

			logger.error("Failed to run Joseph", e);
		} finally {

		}

		return josephCircleResponse;
	}
}
