package com.bankingApp.Banking.app.Service;

import java.util.List;

import com.bankingApp.Banking.app.DTO.UserDto;


public interface UserService {
    
    UserDto createUser(UserDto userDto);
    void deleteUser(long userId);
    UserDto updateUser(long userId,UserDto userDto);
    UserDto changeRoleToAdmin(UserDto userDto);
    UserDto getUserById(long userId);
    List<UserDto> getAllUsers();
    UserDto changeRoleToManager(UserDto userDto);
    UserDto changeRoleToCustomerService(UserDto userDto);
    UserDto changeRoleToUser(UserDto userDto);
    UserDto changeRoleToCashier(UserDto userDto);
    List<String> getUserRole(long userId);
}
