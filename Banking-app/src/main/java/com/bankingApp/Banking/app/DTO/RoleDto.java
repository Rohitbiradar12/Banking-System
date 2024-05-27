package com.bankingApp.Banking.app.DTO;

import java.util.List;

import com.bankingApp.Banking.app.Entity.User;

import lombok.Data;

@Data
public class RoleDto {

    private String roleName;

    private List<UserDto> userDto;
    
}
