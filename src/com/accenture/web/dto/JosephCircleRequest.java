package com.accenture.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class JosephCircleRequest extends DataTransferObject{
	
	@NotNull(message = "{data.null}") 
	@Valid
    private JosephObject circle;

	public JosephObject getCircle() {
		return circle;
	}

	public void setCircle(JosephObject circle) {
		this.circle = circle;
	}
    
}
