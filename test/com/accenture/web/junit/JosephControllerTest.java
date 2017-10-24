package com.accenture.web.junit;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.controller.JosephController;
import com.accenture.web.dto.JosephCircle;
import com.accenture.web.dto.JosephRequest;
import com.accenture.web.dto.JosephResponse;

@RunWith(MockitoJUnitRunner.class)
public class JosephControllerTest {

	@InjectMocks
	private JosephController josephController;

	@Mock
	private JosephBusiness josephBusiness;
	
    private MockMvc mockMvc;
    
    @Mock
	BindingResult result ;

	@Before
	public void setUp() throws Exception { 
		mockMvc = MockMvcBuilders.standaloneSetup(josephController).build();  
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	JosephRequest josephRequest = new JosephRequest();
	JosephResponse josephResponse = new JosephResponse();
	JosephCircle josephCircle = new JosephCircle();

	@Test
	public void testJosephBusiness() throws Exception {	
		
		josephResponse.setLastPeople("w");
		josephResponse.setErrors(null);
		
		when(josephBusiness.callJoseph(Mockito.any(JosephRequest.class))).thenReturn(josephResponse);
		
		String requestBody = "{\"circle\":{\"start\":1, \"interval\":3, \"persons\":[2,\"w\",1]}}"; 
		MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.post("/JosephController")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.accept(MediaType.APPLICATION_JSON))
			.andReturn();
		
		String responseBody = "{\"lastPeople\":\"w\",\"errors\":null}";
		Assert.assertEquals(responseBody,mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetLastPeople() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("w");
		list.add("1");
		josephCircle.setStart(1);
		josephCircle.setInterval(3);
		josephCircle.setPersons(list);
		josephRequest.setCircle(josephCircle);
		
		josephResponse.setLastPeople("w");
		josephResponse.setErrors(null);
		
		when(josephBusiness.callJoseph(josephRequest)).thenReturn(josephResponse);
		when(result.hasErrors()).thenReturn(false);
		
		Assert.assertEquals("w",josephController.getLastPeople( josephRequest,result).getLastPeople());		
	}
	
	@Test
	public void testGetLastPeopleFail() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("w");
		list.add("1");
		josephCircle.setStart(0);
		josephCircle.setInterval(3);
		josephCircle.setPersons(list);
		josephRequest.setCircle(josephCircle);
		
		when(josephBusiness.callJoseph(josephRequest)).thenReturn(josephResponse);
		when(result.hasErrors()).thenReturn(false);
		
		Assert.assertEquals(null,josephController.getLastPeople( josephRequest,result).getLastPeople());		
	}
	
}
