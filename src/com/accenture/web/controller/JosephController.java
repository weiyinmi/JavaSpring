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

import com.accenture.web.business.impl.JosephBusinessImpl;
import com.accenture.web.dto.JosephRequest;
import com.accenture.web.dto.JosephResponse;
import com.accenture.web.dto.JosephErrorMessage;

@Controller
public class JosephController {

	private static final Logger logger = Logger.getLogger(JosephController.class);

	JosephResponse josephCircleResponse = null;

	private JosephBusinessImpl josephBusiness;

	public JosephBusinessImpl getJosephBusiness() {
		return josephBusiness;
	}

	public void setJosephBusiness(JosephBusinessImpl josephBusiness) {
		this.josephBusiness = josephBusiness;
	}

	@RequestMapping("/JosephController")
	public @ResponseBody JosephResponse getLastPeople(@Valid @RequestBody JosephRequest josephCircleRequest,
			BindingResult result) {

		try {

			if (result.hasErrors()) {

				logger.error("Inputs are illegal!");

				List<FieldError> errorList = result.getFieldErrors();
				List<JosephErrorMessage> stringErr = new ArrayList<>();
				JosephErrorMessage josephErrorMessage = null;

				for (FieldError fieldError : errorList) {

					josephErrorMessage = new JosephErrorMessage();
					josephErrorMessage.setField(fieldError.getField());
					josephErrorMessage.setMessage(fieldError.getDefaultMessage());
					stringErr.add(josephErrorMessage);

					logger.error("Error: " + fieldError.getField() + " " + fieldError.getDefaultMessage());
				}

				josephCircleResponse = new JosephResponse();
				josephCircleResponse.setErrors(stringErr); // must new a object,
															// or NullPointer
				josephCircleResponse.setLastPeople(null);

				return josephCircleResponse;
			}

			josephCircleResponse = josephBusiness.callJoseph(josephCircleRequest);
			logger.info("Succeed  to run Joseph");

		} catch (Exception e) {

			logger.error("Failed to run Joseph", e);
		}

		return josephCircleResponse;
	}
}
