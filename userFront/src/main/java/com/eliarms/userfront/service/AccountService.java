package com.eliarms.userfront.service;

import java.security.Principal;

import com.eliarms.userfront.domain.PrimaryAccount;
import com.eliarms.userfront.domain.PrimaryTransaction;
import com.eliarms.userfront.domain.SavingsAccount;
import com.eliarms.userfront.domain.SavingsTransaction;

public interface AccountService {
	PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount , Principal principal);
    void withdraw(String accountType, double amount , Principal principal);

    
}
