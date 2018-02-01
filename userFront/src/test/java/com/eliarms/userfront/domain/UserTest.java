package com.eliarms.userfront.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	User user;
	@Before
	public void setUp()
	{
		user = new User();
	}
	
	
	@Test
	public void getUserId() throws Exception {
		Long  idValue = 4l;
		user.setUserId(idValue);
		assertEquals(idValue,user.getUserId());
		
	}

}
