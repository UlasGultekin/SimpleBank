package com.eteration.simpleBank.simpleBank.repsitories;

import com.eteration.simpleBank.simpleBank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountNumberEqualsOrderByIdAsc(String accountNumber);



}