package com.accenture.web.junit;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.accenture.web.business.JosephBusiness;
import com.accenture.web.business.impl.JosephBusinessImpl;
import com.accenture.web.dto.JosephCircle;
import com.accenture.web.dto.JosephRequest;
import com.accenture.web.service.JosephService;

@RunWith(MockitoJUnitRunner.class)
public class JosephBusinessTest {

	@InjectMocks
	private JosephBusiness business = new JosephBusinessImpl();

	@Mock
	private JosephService service;

	JosephCircle josephCircle = new JosephCircle();
	JosephRequest josephRequest = new JosephRequest();

	@Test
	public void solve() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("w");
		list.add("1");
		josephCircle.setStart(1);
		josephCircle.setInterval(3);
		josephCircle.setPersons(list);
		josephRequest.setCircle(josephCircle);

		when(service.josephFunction(list, 1, 3)).thenReturn("w");

		Assert.assertEquals("w", business.callJoseph(josephRequest).getLastPeople());
	}

	@Test
	public void solveWhenNull() throws Exception {
		List<String> list = new ArrayList<>();
		josephCircle.setStart(1);
		josephCircle.setInterval(3);
		josephCircle.setPersons(list);
		josephRequest.setCircle(josephCircle);

		when(service.josephFunction(list, 1, 3)).thenReturn(null);

		Assert.assertEquals(null, business.callJoseph(josephRequest).getLastPeople());

	}

	@Test
	public void solveWhenIllegal() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("2");
		list.add("w");
		list.add("1");
		josephCircle.setStart(1);
		josephCircle.setInterval(-1);
		josephCircle.setPersons(list);
		josephRequest.setCircle(josephCircle);

		when(service.josephFunction(list, 1, -1)).thenReturn(null).thenThrow(new RuntimeException("Exception!"));

		Assert.assertEquals(null, business.callJoseph(josephRequest).getLastPeople());
	}
}
