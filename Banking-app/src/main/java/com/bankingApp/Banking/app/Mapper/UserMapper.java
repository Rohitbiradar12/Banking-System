package com.bankingApp.Banking.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.Roles;
import com.bankingApp.Banking.app.Entity.User;
import com.bankingApp.Banking.app.Repository.RoleRepository;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());

        if (!user.getRoles().isEmpty()) {
            userDto.setRole(user.getRoles().get(0).getRoleName());
        }

        return userDto;
    }
public User mapToUser(UserDto userDto) {
    if (userDto == null) {
        return null;
    }
    User user = new User();
    user.setUserId(userDto.getUserId());
    user.setUserName(userDto.getUserName());
    user.setPassword(userDto.getPassword());
    
    // Handle single role name
    if (userDto.getRole() != null) {
        Roles role = roleRepository.findByRoleName(userDto.getRole())
            .orElseThrow(() -> new RuntimeException("Role not found: " + userDto.getRole()));
        user.setRoles(Collections.singletonList(role)); // Set single role
    } else {
        user.setRoles(Collections.emptyList()); // Set empty list if no role is provided
    }

    return user;
}

}
