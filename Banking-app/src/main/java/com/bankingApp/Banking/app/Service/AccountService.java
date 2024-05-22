package com.bankingApp.Banking.app.Service;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;

public interface AccountService {

    AccountServiceDto createAccount(AccountServiceDto accountServiceDto);

    AccountServiceDto getAccountById(Long id);

    AccountServiceDto depositMoney(Long id,double amount);



    
} 


    
