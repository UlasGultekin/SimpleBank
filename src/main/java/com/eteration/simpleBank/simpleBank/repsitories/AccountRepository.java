package com.eteration.simpleBank.simpleBank.repsitories;

import com.eteration.simpleBank.simpleBank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}