package com.bankingApp.Banking.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;


    @PostMapping
    public ResponseEntity<AccountServiceDto> addAccount(AccountServiceDto accountServiceDto){
        return new ResponseEntity<>(accountService.createAccount(accountServiceDto),HttpStatus.CREATED);
    }
}
