package com.eteration.simpleBank.simpleBank.services.impl;


import com.eteration.simpleBank.simpleBank.model.Account;
import com.eteration.simpleBank.simpleBank.repsitories.AccountRepository;
import com.eteration.simpleBank.simpleBank.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
   private final AccountRepository accountRepository;


    public ResponseEntity<?> createAcoount(Account account){
        Map<String, Object> hashMap=new LinkedHashMap<>();
        LocalDateTime now = LocalDateTime.now();
        account.setCreateDate(now);
        accountRepository.save(account);
        hashMap.put("status",true);
        hashMap.put("result",account);

        return new ResponseEntity<>(hashMap, HttpStatus.CREATED);
    }

    public ResponseEntity<?> update(Account account){
        Map<String,Object> hashMap = new LinkedHashMap<>();
        try{
            Optional<Account> optionalCategory = accountRepository.findById(account.getId());
            if(optionalCategory.isPresent()){
                accountRepository.saveAndFlush(account);
                hashMap.put("status", true);
                hashMap.put("result", account);
                return new  ResponseEntity(hashMap, HttpStatus.OK);
            }else{
                hashMap.put("status", false);
                return new  ResponseEntity(hashMap, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            hashMap.put("status", false);
            hashMap.put("message", ex.getMessage());
            return new  ResponseEntity(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id){
        Map<String,Object> hashMap =new LinkedHashMap<>();
        try {
            Optional<Account> optionalCategory = accountRepository.findById(id);
            if (optionalCategory.isPresent()){
            accountRepository.deleteById(id);
            hashMap.put("status",true);
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
            }else {
                hashMap.put("status",false);
                hashMap.put("message","Not Found Account");
                return new ResponseEntity<>(hashMap, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            hashMap.put("status",false);
            hashMap.put("message",ex.getMessage());
            return new ResponseEntity<>(hashMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> list(){
        Map<String,Object> hashMap =new LinkedHashMap<>();
        hashMap.put("status",true);
        hashMap.put("result",accountRepository.findAll());
        return new ResponseEntity<>(hashMap, HttpStatus.OK);
    }

}
