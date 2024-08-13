package com.bankingApp.Banking.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingApp.Banking.app.Entity.Roles;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    
    // Method to find a role by its name
    Optional<Roles> findByRoleName(String roleName);
}
