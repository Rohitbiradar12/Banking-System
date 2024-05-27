package com.bankingApp.Banking.app.Mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.User;

@Component
public class UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    public  User mapToUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        user.setRoles(userDto.getRolesDto().stream()
            .map(roleMapper::mapToRole)
            .collect(Collectors.toList()));
        return user;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setRolesDto(user.getRoles().stream()
            .map(roleMapper::roleDto)
            .collect(Collectors.toList()));
        return userDto;
    }
}
