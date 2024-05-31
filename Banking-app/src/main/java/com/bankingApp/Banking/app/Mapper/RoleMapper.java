package com.bankingApp.Banking.app.Mapper;

import org.springframework.stereotype.Component;

import com.bankingApp.Banking.app.DTO.RoleDto;
import com.bankingApp.Banking.app.Entity.Roles;

@Component
public class RoleMapper {

    public Roles mapToRole(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Roles role = new Roles();
        role.setRoleName(roleDto.getRoleName());
        return role;
    }

    public RoleDto roleDto(Roles role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getRoleName());
        return roleDto;
    }
}
