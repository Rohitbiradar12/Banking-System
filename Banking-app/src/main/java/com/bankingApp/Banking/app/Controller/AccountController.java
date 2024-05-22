package com.bankingApp.Banking.app.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Mapper.AccountMapper;
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


    @GetMapping("/{id}")
    public ResponseEntity<AccountServiceDto> getAccountById(@PathVariable Long id){
        AccountServiceDto accountServiceDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountServiceDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountServiceDto> addAmount(@PathVariable Long id,@RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountServiceDto depositedMoney =  accountService.depositMoney( id,amount);
        return ResponseEntity.ok(depositedMoney);
    }
}
