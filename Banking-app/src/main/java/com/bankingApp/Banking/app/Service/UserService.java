package com.bankingApp.Banking.app.Service;

import java.util.List;

import com.bankingApp.Banking.app.Entity.User;

public interface UserService {
    
    User createUser(User user);
    void deleteUser(long userId);
    User updateUser(long userId,User user);
    User changeRoleToAdmin(User user);
    User getUser(long userId);
    List<User> getAllUsers();
    User changeRoleToManager(User user);
    User changeRoleToCustomerService(User user);
    User changeRoleToUser(User user);
    User changeRoleToCashier(User user);
}
