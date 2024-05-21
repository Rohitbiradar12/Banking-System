package com.bankingApp.Banking.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Entity.Accounts;
import com.bankingApp.Banking.app.Mapper.AccountMapper;
import com.bankingApp.Banking.app.Repository.AccountRepo;
import com.bankingApp.Banking.app.Service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo){
        this.accountRepo = accountRepo;
    }

    @Override
    public AccountServiceDto createAccount(AccountServiceDto accountServiceDto) {
        Accounts accounts = AccountMapper.mapToAccount(accountServiceDto);
        Accounts savedAccount = accountRepo.save(accounts);
        return AccountMapper.mapToAccountDto(savedAccount);
        
    }
    
}
