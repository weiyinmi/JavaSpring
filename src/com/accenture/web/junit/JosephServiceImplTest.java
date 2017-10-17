package com.accenture.web.junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.web.service.impl.JosephServiceImpl;

public class JosephServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	JosephServiceImpl josephServiceImpl = new JosephServiceImpl();	
	List<String> list = new ArrayList<>();
	
	@Test
	public void testJosephFunction() {
		list.add("1");
		list.add("a");
		list.add("2g");
		list.add("3");
		list.add("w");
		assertEquals("1",josephServiceImpl.josephFunction(list, 1, 3));
	}
}
