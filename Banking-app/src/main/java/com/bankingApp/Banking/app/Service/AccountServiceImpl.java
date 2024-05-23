package com.bankingApp.Banking.app.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingApp.Banking.app.DTO.AccountServiceDto;
import com.bankingApp.Banking.app.Entity.Accounts;
import com.bankingApp.Banking.app.Mapper.AccountMapper;
import com.bankingApp.Banking.app.Repository.AccountRepo;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public AccountServiceDto createAccount(AccountServiceDto accountServiceDto) {
        Accounts accounts = AccountMapper.mapToAccount(accountServiceDto);
        Accounts savedAccount = accountRepo.save(accounts);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountServiceDto getAccountById(Long id) {
        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return AccountMapper.mapToAccountDto(accounts);
    }

    @Override
    public AccountServiceDto depositMoney(Long id, double amount) {
        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        Double total = accounts.getBalance() + amount;
        accounts.setBalance(total);
        accountRepo.save(accounts);
        return AccountMapper.mapToAccountDto(accounts);

    }

    @Override
    public AccountServiceDto withdrawMoney(Long id, double amount) {
        Accounts accounts = accountRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found"));
        if (accounts.getBalance() < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        Double total = accounts.getBalance() - amount;
        accounts.setBalance(total);
        Accounts savedAccount = accountRepo.save(accounts);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountServiceDto> getAllAccounts() {
        List<Accounts> accounts = accountRepo.findAll();
        return accounts.stream()
                .map(AccountMapper::mapToAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccountById(Long id) {
        Accounts accounts = accountRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepo.deleteById(id);

    }

}
