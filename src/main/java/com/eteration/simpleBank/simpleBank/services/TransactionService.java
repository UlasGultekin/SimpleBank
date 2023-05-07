package com.eteration.simpleBank.simpleBank.services;

import com.eteration.simpleBank.simpleBank.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface TransactionService {
    List<Transaction> getAllTransactions();
    ResponseEntity<?> getTransactionById(Long id);
    ResponseEntity<?> deleteTransactionById(Long id);
    ResponseEntity<?> saveBillPaymentTransaction(BillPaymentTransaction billPaymentTransaction,String accountNumber);
    ResponseEntity<?> saveCheckTransaction(CheckTransaction checkTransaction,String accountNumber);
    ResponseEntity<?> saveDepositTransaction(DepositTransaction depositTransaction,String accountNumber);
    ResponseEntity<?> savePhoneBillPaymentTransaction(PhoneBillPaymentTransaction phoneBillPaymentTransaction,String accountNumber);
    ResponseEntity<?> saveWithdrawalTransaction(WithdrawalTransaction withdrawalTransaction,String accountNumber);
}
