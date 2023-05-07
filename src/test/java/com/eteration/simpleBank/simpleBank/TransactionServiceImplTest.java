package com.eteration.simpleBank.simpleBank;

import com.eteration.simpleBank.simpleBank.model.Account;
import com.eteration.simpleBank.simpleBank.model.BillPaymentTransaction;
import com.eteration.simpleBank.simpleBank.repsitories.AccountRepository;
import com.eteration.simpleBank.simpleBank.repsitories.TransactionRepository;
import com.eteration.simpleBank.simpleBank.services.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private BillPaymentTransaction billPaymentTransaction;
    private Account account;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        billPaymentTransaction = new BillPaymentTransaction();
        billPaymentTransaction.setAmount(100.0);

        account = new Account("","");
        account.setAccountNumber("1234567890");
        account.setBalance(1000.0);
    }

    @Test
    public void testSaveBillPaymentTransaction() {
        String accountNumber = "1234567890";

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        when(transactionRepository.save(any(BillPaymentTransaction.class))).thenReturn(billPaymentTransaction);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        ResponseEntity<?> responseEntity = transactionService.saveBillPaymentTransaction(billPaymentTransaction, accountNumber);

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(transactionRepository, times(1)).save(any(BillPaymentTransaction.class));
        verify(accountRepository, times(1)).save(any(Account.class));
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testSaveBillPaymentTransactionInsufficientBalance() {
        String accountNumber = "1234567890";
        Double transactionAmount = 2000.0;
        billPaymentTransaction.setAmount(transactionAmount);

        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);

        ResponseEntity<?> responseEntity = transactionService.saveBillPaymentTransaction(billPaymentTransaction, accountNumber);

        verify(accountRepository, times(1)).findByAccountNumber(accountNumber);
        verify(transactionRepository, never()).save(any(BillPaymentTransaction.class));
        verify(accountRepository, never()).save(any(Account.class));
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assertions.assertEquals("Bakiye Yetersiz", responseEntity.getBody());
    }


}
