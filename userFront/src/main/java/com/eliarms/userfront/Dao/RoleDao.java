package com.eliarms.userfront.Dao;

import org.springframework.data.repository.CrudRepository;

import com.eliarms.userfront.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
