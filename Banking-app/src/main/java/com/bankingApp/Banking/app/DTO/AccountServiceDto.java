package com.bankingApp.Banking.app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountServiceDto {

    private int id;
    private String accountholderName;
    private Long balance;


    
}
