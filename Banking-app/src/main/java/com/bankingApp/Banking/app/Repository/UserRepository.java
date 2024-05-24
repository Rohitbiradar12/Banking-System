package com.bankingApp.Banking.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.Banking.app.Entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);
}
