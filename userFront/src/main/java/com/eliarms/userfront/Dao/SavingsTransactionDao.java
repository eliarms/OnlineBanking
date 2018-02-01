package com.eliarms.userfront.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eliarms.userfront.domain.SavingsTransaction;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}

