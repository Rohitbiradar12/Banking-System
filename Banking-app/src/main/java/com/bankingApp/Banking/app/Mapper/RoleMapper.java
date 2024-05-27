package com.bankingApp.Banking.app.Mapper;

import org.springframework.stereotype.Component;

import com.bankingApp.Banking.app.DTO.RoleDto;
import com.bankingApp.Banking.app.Entity.Roles;

@Component
public class RoleMapper {

    public Roles mapToRole(RoleDto roleDto){
        Roles role = new Roles();
        roleDto.setRoleName(role.getRoleName());
        return role;

    }


    public RoleDto roleDto(Roles role){
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getRoleName());
        return roleDto;

    }
    
}
