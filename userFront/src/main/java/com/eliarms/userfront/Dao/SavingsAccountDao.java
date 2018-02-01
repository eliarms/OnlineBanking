package com.eliarms.userfront.Dao;

import com.eliarms.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;


public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (int accountNumber);
}
