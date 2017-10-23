package com.accenture.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.accenture.web.validator.ListSizeLimitation;

public class JosephRequest extends DataTransferObject{
	
	@NotNull(message = "{data.null}") 
	@Valid
    private JosephCircle circle;

	public JosephCircle getCircle() {
		return circle;
	}

	public void setCircle(JosephCircle circle) {
		this.circle = circle;
	}
    
}
