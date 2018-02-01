package com.eliarms.userfront.Dao;

import com.eliarms.userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;


public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount,Long> {

    PrimaryAccount findByAccountNumber (int accountNumber);
}
