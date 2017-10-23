package com.accenture.web.dto;

import java.util.List;

public class JosephResponse extends  DataTransferObject{
    private String lastPeople;
    private List<JosephErrorMessage> errors;
    
	public String getLastPeople() {
		return lastPeople;
	}

	public void setLastPeople(String last) {
		this.lastPeople = last;
	}

	public List<JosephErrorMessage> getErrors() {
		return errors;
	}

	public void setErrors(List<JosephErrorMessage> errors) {
		this.errors = errors;
	}
}
