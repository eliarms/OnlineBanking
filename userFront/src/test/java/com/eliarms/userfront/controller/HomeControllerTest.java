package com.eliarms.userfront.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.eliarms.userfront.service.UserService;

public class HomeControllerTest {

	@Mock
	UserService userService;
	
	@Mock
	Model model;
	HomeController controller;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		controller = new HomeController();
	}

	@Test
	public void getIndexPage() throws Exception {
		String viewName = controller.index();
		assertEquals("index",viewName);
	}

}
