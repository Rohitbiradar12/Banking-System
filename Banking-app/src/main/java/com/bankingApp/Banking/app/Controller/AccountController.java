package com.bankingApp.Banking.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountServiceDto> createAccount(@RequestBody AccountServiceDto accountServiceDto) {
        AccountServiceDto createdAccount = accountService.createAccount(accountServiceDto);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
}
