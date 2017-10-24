package com.accenture.web.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.accenture.web.validator.ListSizeLimitation;
import com.accenture.web.validator.NoRepetitionList;

@ListSizeLimitation(listFieldName="persons",message="{data.size.limitation}",limitedFieldName = "start")
@ListSizeLimitation(listFieldName="persons",message="{data.size.limitation}",limitedFieldName = "interval")
public class JosephCircle extends DataTransferObject{
	
	@NotNull(message = "{data.null}")
	@Min(value=1,message = "{data.min}")
	private Integer start;
	
	@NotNull(message = "{data.null}")
	@Min(value=0,message = "{data.min}")
	private Integer interval;
	
	@NotEmpty(message = "{data.empty}")
	@NoRepetitionList(message = "{data.repetition}")
	private List<String> persons;

	public List<String> getPersons() {
		return persons;
	}

	public void setPersons(List<String> persons) {
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
