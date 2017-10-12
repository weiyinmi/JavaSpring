package com.accenture.web.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class JosephObject extends DataTransferObject{
	
	@NotNull(message = "{JosephObject.data.null}")
	@Min(value=1,message = "{JosephObject.data.min}")
//	@Digits(integer=9,fraction=0,message = "{JosephObject.data.digit}")
	private Integer start;
	
	@NotNull(message = "{JosephObject.data.null}")
	@Min(value=0,message = "{JosephObject.data.min}")
	private Integer interval;
	
	@NotEmpty(message = "{JosephObject.data.empty}")  
	private String[] persons;

	public String[] getPersons() {
		return persons;
	}

	public void setPersons(String[] persons) {
		this.persons = persons;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

}
