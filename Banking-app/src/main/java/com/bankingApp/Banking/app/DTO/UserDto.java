package com.bankingApp.Banking.app.DTO;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.bankingApp.Banking.app.Entity.Roles;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Data;

@Data
public class UserDto {

    private Long userId;

    private String userName;

    private String password;

    private List<RoleDto> rolesDto;
}
