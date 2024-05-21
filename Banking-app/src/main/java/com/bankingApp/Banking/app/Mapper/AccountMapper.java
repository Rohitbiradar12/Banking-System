package com.bankingApp.Banking.app.Mapper;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Entity.Accounts;

public class AccountMapper {
    
    public static Accounts mapToAccount(AccountServiceDto accountServiceDto) {
        return new Accounts(
            accountServiceDto.getId(),
            accountServiceDto.getAccountholderName(),
            accountServiceDto.getBalance()
        );
    }

    public static AccountServiceDto mapToAccountDto(Accounts accounts) {
        return new AccountServiceDto(
            accounts.getId(),
            accounts.getAccountholderName(),
            accounts.getBalance()
        );
    }
}
