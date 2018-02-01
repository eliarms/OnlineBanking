package com.eliarms.userfront.service;

import java.util.Set;

import com.eliarms.userfront.domain.User;
import com.eliarms.userfront.domain.security.UserRole;
public interface UserService {
	
	User findByUsername(String username);
	User findByEmail(String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	boolean checkUserExists(String username, String email);
	void save(User user);
	User createUser(User user,Set<UserRole> userRoles);

}
