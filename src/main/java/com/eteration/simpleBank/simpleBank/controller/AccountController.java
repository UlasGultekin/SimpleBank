package com.eteration.simpleBank.simpleBank.controller;

import com.eteration.simpleBank.simpleBank.model.Account;
import com.eteration.simpleBank.simpleBank.services.impl.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {
   private final AccountServiceImpl accountServiceImpl;

    @PostMapping("/create")
    public ResponseEntity createAccount(@RequestBody  Account account){
        return accountServiceImpl.createAcoount(account);
    }



    @PutMapping("/update")
    public ResponseEntity updateAccount(@RequestBody  Account account){
        return accountServiceImpl.update(account);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(Long id){
        return accountServiceImpl.delete(id);
    }

    @GetMapping("/list")
    public ResponseEntity getAllAccount(){
        return accountServiceImpl.list();
    }

}
