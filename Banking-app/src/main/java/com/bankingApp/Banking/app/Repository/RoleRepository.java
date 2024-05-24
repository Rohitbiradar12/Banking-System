package com.bankingApp.Banking.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingApp.Banking.app.Entity.Roles;

public interface RoleRepository extends JpaRepository<Roles,Long> {

    Roles findRoleByRoleName(String roleName);
    
}
