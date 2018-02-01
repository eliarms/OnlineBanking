package com.eliarms.userfront.service;

import java.security.Principal;
import java.util.List;

import com.eliarms.userfront.domain.PrimaryAccount;
import com.eliarms.userfront.domain.PrimaryTransaction;

import com.eliarms.userfront.domain.SavingsAccount;
import com.eliarms.userfront.domain.SavingsTransaction;

public interface TransactionService {
	List<PrimaryTransaction> findPrimaryTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
    
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
    
    }
