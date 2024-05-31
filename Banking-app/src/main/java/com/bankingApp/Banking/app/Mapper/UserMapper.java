package com.bankingApp.Banking.app.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.User;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    public UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setRolesDto(user.getRoles().stream()
            .map(roleMapper::roleDto)
            .collect(Collectors.toList()));
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
        user.setRoles(userDto.getRolesDto().stream()
            .map(roleMapper::mapToRole)
            .collect(Collectors.toList()));
        return user;
    }
}
