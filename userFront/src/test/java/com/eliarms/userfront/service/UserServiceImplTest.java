package com.eliarms.userfront.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eliarms.userfront.Dao.UserDao;
import com.eliarms.userfront.domain.User;
import com.eliarms.userfront.service.UserServiceImpl.UserServiceImpl;

public class UserServiceImplTest {
	UserServiceImpl userservice;
	@Mock
	UserDao userdao;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}

	@Test
	public void findByUsername() {
		
	}

}
