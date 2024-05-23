package com.bankingApp.Banking.app.Service;

import java.util.List;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;

public interface AccountService {

    AccountServiceDto createAccount(AccountServiceDto accountServiceDto);

    AccountServiceDto getAccountById(Long id);

    AccountServiceDto depositMoney(Long id,double amount);

    AccountServiceDto withdrawMoney(Long id,double amount);

    List<AccountServiceDto> getAllAccounts();

    void deleteAccountById(Long id);



    
} 


    
