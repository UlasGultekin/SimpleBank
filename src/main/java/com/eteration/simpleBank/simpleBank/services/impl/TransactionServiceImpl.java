package com.eteration.simpleBank.simpleBank.services.impl;

import com.eteration.simpleBank.simpleBank.enums.REnum;
import com.eteration.simpleBank.simpleBank.model.*;
import com.eteration.simpleBank.simpleBank.repsitories.AccountRepository;
import com.eteration.simpleBank.simpleBank.repsitories.TransactionRepository;
import com.eteration.simpleBank.simpleBank.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public ResponseEntity<?> getTransactionById(Long id) {
        Map<REnum,Object> hashMap =new LinkedHashMap<>();
        Optional optionalTransaction=transactionRepository.findById(id);
       try {

           if (optionalTransaction.isPresent()){
               hashMap.put(REnum.status,true);
               hashMap.put(REnum.result,hashMap);
               return new ResponseEntity<>(hashMap, HttpStatus.OK);
           }
           else {
               hashMap.put(REnum.status,false);
               hashMap.put(REnum.message,"Kayıt bulunamadı");
               return new ResponseEntity<>(hashMap,HttpStatus.NOT_FOUND);
           }
       }catch (Exception ex){
           hashMap.put(REnum.status,false);
           hashMap.put(REnum.message,ex.getMessage());
           return new ResponseEntity<>(hashMap,HttpStatus.BAD_REQUEST);
       }

    }



    @Override
    public ResponseEntity<?> deleteTransactionById(Long id) {
        Map<REnum,Object> hashMap =new LinkedHashMap<>();
        try {
            transactionRepository.deleteById(id);
            hashMap.put(REnum.status,true);
            return new ResponseEntity<>(hashMap, HttpStatus.OK);

        }catch (Exception ex){
            hashMap.put(REnum.status,false);
            hashMap.put(REnum.message,ex.getMessage());
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> saveBillPaymentTransaction(BillPaymentTransaction billPaymentTransaction,String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber);
        String uuid = java.util.UUID.randomUUID().toString();
        Double newBalance = account.getBalance();
        billPaymentTransaction.setApprovalCode(uuid);
        Double transactionAmount = billPaymentTransaction.getAmount();
        if (newBalance<transactionAmount){
            return new ResponseEntity<>("Bakiye Yetersiz",HttpStatus.BAD_REQUEST);

        }
        newBalance-=transactionAmount;
        account.setBalance(newBalance);
        account.getTransactions().add(billPaymentTransaction);

        BillPaymentTransaction savedTransaction = transactionRepository.save(billPaymentTransaction);
        accountRepository.save(account);
        return ResponseEntity.ok(savedTransaction);
    }

    @Override
    public ResponseEntity<?> saveCheckTransaction(CheckTransaction checkTransaction,String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        String uuid = java.util.UUID.randomUUID().toString();
        Double newBalance = account.getBalance();
        checkTransaction.setApprovalCode(uuid);
        Double transactionAmount = checkTransaction.getAmount();
        if (newBalance<transactionAmount){
            return new ResponseEntity<>("Bakiye Yetersiz",HttpStatus.BAD_REQUEST);
        }
        newBalance-=transactionAmount;
        account.setBalance(newBalance);
        account.getTransactions().add(checkTransaction);
        CheckTransaction savedTransaction = transactionRepository.save(checkTransaction);
        accountRepository.save(account);

        return ResponseEntity.ok(savedTransaction);
    }

    @Override
    public ResponseEntity<?> saveDepositTransaction(DepositTransaction depositTransaction,String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        String uuid = java.util.UUID.randomUUID().toString();
        Double newBalance = account.getBalance();
        depositTransaction.setApprovalCode(uuid);
        Double transactionAmount = depositTransaction.getAmount();

        newBalance+=transactionAmount;
        account.setBalance(newBalance);
        account.getTransactions().add(depositTransaction);

        DepositTransaction savedTransaction = transactionRepository.save(depositTransaction);
        accountRepository.save(account);

        return ResponseEntity.ok(savedTransaction);
    }

    @Override
    public ResponseEntity<?> savePhoneBillPaymentTransaction(PhoneBillPaymentTransaction phoneBillPaymentTransaction,String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        String uuid = java.util.UUID.randomUUID().toString();
        Double newBalance = account.getBalance();
        phoneBillPaymentTransaction.setApprovalCode(uuid);
        Double transactionAmount = phoneBillPaymentTransaction.getAmount();
        if (newBalance<transactionAmount){
            return new ResponseEntity<>("Bakiye Yetersiz",HttpStatus.BAD_REQUEST);

        }
        newBalance-=transactionAmount;
        account.setBalance(newBalance);
        account.getTransactions().add(phoneBillPaymentTransaction);

        BillPaymentTransaction savedTransaction = transactionRepository.save(phoneBillPaymentTransaction);
        accountRepository.save(account);

            return ResponseEntity.ok(savedTransaction);
    }

    @Override
    public ResponseEntity<?> saveWithdrawalTransaction(WithdrawalTransaction withdrawalTransaction,String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        String uuid = java.util.UUID.randomUUID().toString();
        Double newBalance = account.getBalance();
        withdrawalTransaction.setApprovalCode(uuid);
        Double transactionAmount = withdrawalTransaction.getAmount();
        if (newBalance<transactionAmount){
            return new ResponseEntity<>("Bakiye Yetersiz",HttpStatus.BAD_REQUEST);
        }
        newBalance-=transactionAmount;
        account.setBalance(newBalance);
        account.getTransactions().add(withdrawalTransaction);

        WithdrawalTransaction savedTransaction = transactionRepository.save(withdrawalTransaction);
        accountRepository.save(account);

        return ResponseEntity.ok(savedTransaction);
    }



}
