package com.eliarms.userfront.service.UserServiceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eliarms.userfront.Dao.RoleDao;
import com.eliarms.userfront.Dao.UserDao;
import com.eliarms.userfront.domain.User;
import com.eliarms.userfront.domain.security.UserRole;
import com.eliarms.userfront.service.AccountService;
import com.eliarms.userfront.service.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService{
private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountService accountService;
	

	
	public void save(User user)
	{
		userDao.save(user);
	}
	
	public User findByUsername(String username)
	{
		return userDao.findByUsername(username);
	}
	
	public User findByEmail(String email)
	{
		return userDao.findByEmail(email);
	}
	
	public boolean checkUserExists(String username, String email)
	{
		if(checkUsernameExists(username)|| checkEmailExists(email))
		{
		return true;
		}
		else
		{
			return false;
		}
	}

	public boolean checkUsernameExists(String username) {
		if(null != findByUsername(username))
		{
			return true;
		}
		return false;
	}
	
	public boolean checkEmailExists(String email) {
		if(null != findByEmail(email))
		{
			return true;
		}
		return false;
	}
	
	 public User createUser(User user, Set<UserRole> userRoles) {
	        User localUser = userDao.findByUsername(user.getUsername());

	        if (localUser != null) {
	            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
	        } else {
	            String encryptedPassword = passwordEncoder.encode(user.getPassword());
	            user.setPassword(encryptedPassword);

	            for (UserRole ur : userRoles) {
	                roleDao.save(ur.getRole());
	            }

	            user.getUserRoles().addAll(userRoles);

	            user.setPrimaryAccount(accountService.createPrimaryAccount());
	            user.setSavingsAccount(accountService.createSavingsAccount());

	            localUser = userDao.save(user);
	        }

	        return localUser;
	    }
	    
}