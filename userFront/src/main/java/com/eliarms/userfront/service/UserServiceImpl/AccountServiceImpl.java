package com.eliarms.userfront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliarms.userfront.Dao.PrimaryAccountDao;
import com.eliarms.userfront.Dao.SavingsAccountDao;
import com.eliarms.userfront.domain.PrimaryAccount;
import com.eliarms.userfront.domain.PrimaryTransaction;
import com.eliarms.userfront.domain.SavingsAccount;
import com.eliarms.userfront.domain.SavingsTransaction;
import com.eliarms.userfront.domain.User;
import com.eliarms.userfront.service.AccountService;
import com.eliarms.userfront.service.TransactionService;
import com.eliarms.userfront.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {
	
	private static int nextAccountNumber = 11223145;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;
    
    
    @Autowired
    private TransactionService transactionService;
    

    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        primaryAccount.setAccountNumber(accountGen());

        primaryAccountDao.save(primaryAccount);

        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountNumber(accountGen());

        savingsAccountDao.save(savingsAccount);

        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }
    
    
    private int accountGen() {
        return ++nextAccountNumber;
    }
    public void deposit(String accountType, double amount , Principal principal){
    	User user = userService.findByUsername(principal.getName());
    	if(accountType.equalsIgnoreCase("Primary"))
    	{
    		PrimaryAccount primaryAccount = user.getPrimaryAccount();
    		primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
    		primaryAccountDao.save(primaryAccount);
    		Date date = new Date();
    		PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Deposit to Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(), primaryAccount);
    		transactionService.savePrimaryDepositTransaction(primaryTransaction);
    	}
    	
    	else if(accountType.equalsIgnoreCase("Savings"))
    	{
    		SavingsAccount savingsAccount = user.getSavingsAccount();
    		savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
    		savingsAccountDao.save(savingsAccount);
    		Date date = new Date();
    		SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Deposit to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(), savingsAccount);
    		 transactionService.saveSavingsDepositTransaction(savingsTransaction);
    	}
    }
    

    
    public void withdraw(String accountType, double amount , Principal principal){
    	
    	User user = userService.findByUsername(principal.getName());
    	BigDecimal myamount = BigDecimal.valueOf(amount);
    	if(accountType.equalsIgnoreCase("Primary"))
    	{
    		PrimaryAccount primaryAccount = user.getPrimaryAccount();
    		BigDecimal pbalance = primaryAccount.getAccountBalance();
    		
    		if (pbalance.compareTo(myamount) <0){
    			throw new RuntimeException("Insufisant Amount");
    		
    		}
    		else{
    		
    		primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
    		primaryAccountDao.save(primaryAccount);
    		Date date = new Date();
    		PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"withdraw from Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(), primaryAccount);
    		transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
    		}
    		}
    	
    	else if(accountType.equalsIgnoreCase("Savings"))
    	{
    		SavingsAccount savingsAccount = user.getSavingsAccount();
    		BigDecimal sbalance = savingsAccount.getAccountBalance();
    		if (sbalance.compareTo(myamount) <0){
    			throw new RuntimeException("Insufisant Amount");
    		
    		}
    		else
    		{
    		savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
    		savingsAccountDao.save(savingsAccount);
    		Date date = new Date();
    		SavingsTransaction savingsTransaction = new SavingsTransaction(date,"withdraw from Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(), savingsAccount);
    		transactionService.saveSavingsWithdrawTransaction(savingsTransaction);    	}
    	}
    	}
	

}
