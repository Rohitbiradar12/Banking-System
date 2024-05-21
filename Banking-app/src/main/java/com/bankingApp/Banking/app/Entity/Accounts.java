package com.bankingApp.Banking.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Accounts {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountholderName;
    private Double balance;


    public Accounts() {
    }

    // Parameterized constructor
    public Accounts(Long id, String accountholderName, Double balance) {
        this.id = id;
        this.accountholderName = accountholderName;
        this.balance = balance;
    }
    
}
