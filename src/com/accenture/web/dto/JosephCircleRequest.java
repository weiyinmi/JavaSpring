package com.accenture.web.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.accenture.web.validator.ListSizeLimitation;

public class JosephCircleRequest extends DataTransferObject{
	
	@NotNull(message = "{data.null}") 
	@Valid
	@ListSizeLimitation(listFieldName="persons",message="{data.size.limitation}",limitedFieldName = "start")
	@ListSizeLimitation(listFieldName="persons",message="{data.size.limitation}",limitedFieldName = "interval")
    private JosephObject circle;

	public JosephObject getCircle() {
		return circle;
	}

	public void setCircle(JosephObject circle) {
		this.circle = circle;
	}
    
}
