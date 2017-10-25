package com.accenture.web.junit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

	@InjectMocks
	private JosephController josephController;

	@Mock
	private JosephBusiness josephBusiness;

	private MockMvc mockMvc;

	@Mock
	BindingResult result;

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
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/JosephController")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = "{\"lastPeople\":\"w\",\"errors\":null}";
		Assert.assertEquals(responseBody, mvcResult.getResponse().getContentAsString());
	}

	@Test
	public void testGetLastPeopleNullValue() throws Exception {
		// when(josephBusiness.callJoseph(Mockito.any(JosephRequest.class))).thenReturn(josephResponse);

		String requestBody = "{\"circle\":{\"start\":1, \"interval\":null, \"persons\":[2,\"w\",1]}}";
		mockMvc.perform(MockMvcRequestBuilders.post("/JosephController").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
				.andExpect(jsonPath("$.errors[0].field").value("circle.interval"))
				.andExpect(jsonPath("$.errors[0].message").value("{data.null}")).andReturn();
	}

	@Test
	public void testGetLastPeopleRepeat() throws Exception {

		String requestBody = "{\"circle\":{\"start\":1, \"interval\":2, \"persons\":[2,\"w\",1,\"w\"]}}";
		mockMvc.perform(MockMvcRequestBuilders.post("/JosephController").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
				.andExpect(jsonPath("$.errors[0].field").value("circle.persons"))
				.andExpect(jsonPath("$.errors[0].message").value("{data.repetition}")).andReturn();
	}

	@Test
	public void testGetLastPeopleLimitSize() throws Exception {

		String requestBody = "{\"circle\":{\"start\":5, \"interval\":2, \"persons\":[2,\"w\",1,\"w\"]}}";
		mockMvc.perform(MockMvcRequestBuilders.post("/JosephController").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
				.andExpect(jsonPath("$.errors[0].field").value("circle.start"))
				.andExpect(jsonPath("$.errors[0].message").value("{data.size.limitation}"))
				.andExpect(jsonPath("$.errors[1].field").value("circle.persons"))
				.andExpect(jsonPath("$.errors[1].message").value("{data.repetition}")).andReturn();
	}

	@Test
	public void testGetLastPeopleBadRequest() throws Exception {

		String requestBody = "{\"circle\":{\"start\":1, \"interval\":\"a\", \"persons\":[2,\"w\",1]}}";
		mockMvc.perform(MockMvcRequestBuilders.post("/JosephController").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andReturn();
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

		Assert.assertEquals("w", josephController.getLastPeople(josephRequest, result).getLastPeople());
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

		Assert.assertEquals(null, josephController.getLastPeople(josephRequest, result).getLastPeople());
	}

}
