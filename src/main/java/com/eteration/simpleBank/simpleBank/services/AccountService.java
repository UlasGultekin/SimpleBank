package com.eteration.simpleBank.simpleBank.services;

import com.eteration.simpleBank.simpleBank.model.Account;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    public ResponseEntity<?> createAcoount(Account account);
    public ResponseEntity<?> update(Account account);
    public ResponseEntity<?> delete(Long id);
    public ResponseEntity<?> list();


}
