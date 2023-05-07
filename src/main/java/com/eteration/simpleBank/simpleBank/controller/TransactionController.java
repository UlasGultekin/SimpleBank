package com.eteration.simpleBank.simpleBank.controller;

import com.eteration.simpleBank.simpleBank.model.*;
import com.eteration.simpleBank.simpleBank.repsitories.TransactionRepository;
import com.eteration.simpleBank.simpleBank.services.impl.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("transaction")
public class TransactionController {
       private final TransactionServiceImpl transactionService;




    @PostMapping("/save/phone-bill-transaction")
    public ResponseEntity<?> savePhoneBillTransaction(@RequestBody PhoneBillPaymentTransaction phoneBillPaymentTransaction,@PathVariable String accountNumber) {
        return transactionService.savePhoneBillPaymentTransaction(phoneBillPaymentTransaction,accountNumber);
    }
    @PostMapping("/save/withdrawal-transaction")
    public ResponseEntity<?> saveWithdrawalTransaction(@RequestBody WithdrawalTransaction withdrawalTransaction,@PathVariable String accountNumber) {
        return transactionService.saveWithdrawalTransaction(withdrawalTransaction,accountNumber);
    }
    @PostMapping("/save/deposit-transaction")
    public ResponseEntity<?> saveDepositTransaction(@RequestBody DepositTransaction depositTransaction,@PathVariable String accountNumber) {
        return transactionService.saveDepositTransaction(depositTransaction,accountNumber);
    }
    @PostMapping("/save/bill-payment-transaction/{accountNumber}")
    public ResponseEntity<?> saveBillPayment(@RequestBody BillPaymentTransaction billPaymentTransaction,@PathVariable String accountNumber) {
        return transactionService.saveBillPaymentTransaction(billPaymentTransaction,accountNumber);
    }
    @PostMapping("/save/check-payment-transaction")
    public ResponseEntity<?> saveCheck(@RequestBody CheckTransaction checkTransaction,@PathVariable String accountNumber) {
        return transactionService.saveCheckTransaction(checkTransaction,accountNumber);
    }





    @GetMapping("list")
    public ResponseEntity<?> list(Long id){
        return transactionService.getTransactionById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long id){
        return transactionService.deleteTransactionById(id);
    }
}
