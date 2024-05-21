package com.bankingApp.Banking.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountServiceDto {

    private Long id;
    private String accountholderName;
    private Double balance;

    public AccountServiceDto(){

    }

    public AccountServiceDto(Long id, String accountholderName, Double balance) {
        this.id = id;
        this.accountholderName = accountholderName;
        this.balance = balance;
    }


    
}
