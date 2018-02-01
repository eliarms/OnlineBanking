package com.eliarms.userfront.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eliarms.userfront.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
