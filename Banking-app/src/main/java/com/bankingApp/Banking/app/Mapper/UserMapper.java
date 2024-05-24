package com.bankingApp.Banking.app.Mapper;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.User;

public class UserMapper {
    public User mapToUser(UserDto userDto){
        User user=new User();
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        return user;
    }
    public UserDto mapToUserDto(User user){
        UserDto userDto=new UserDto();
        user.setUserName(user.getUserName());
        user.setPassword(user.getPassword());
        return userDto;
    }
}
