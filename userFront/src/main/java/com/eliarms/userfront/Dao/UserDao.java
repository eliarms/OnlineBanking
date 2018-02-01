package com.eliarms.userfront.Dao;

import org.springframework.data.repository.CrudRepository;

import com.eliarms.userfront.domain.User;

public interface UserDao extends CrudRepository<User, Long>{
	User findByUsername(String username);
	User findByEmail(String email);


}
