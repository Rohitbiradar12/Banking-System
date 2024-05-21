package com.bankingApp.Banking.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.Banking.app.Entity.Accounts;

public interface AccountRepo extends JpaRepository<Accounts,Integer> {
    
}
