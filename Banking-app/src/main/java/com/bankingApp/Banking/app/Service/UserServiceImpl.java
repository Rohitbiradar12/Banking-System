package com.bankingApp.Banking.app.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingApp.Banking.app.Entity.Roles;
import com.bankingApp.Banking.app.Entity.User;
import com.bankingApp.Banking.app.Repository.RoleRepository;
import com.bankingApp.Banking.app.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private static final String ADMIN = "ADMIN";
    private static final String CUSTOMER_SERVICE = "CUSTOMERSERVICE";
    private static final String MANAGER = "MANAGER";
    private static final String USER = "USER";
    private static final String CASHIER = "CASHIER";

    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        User createdUser = userRepository.save(user);
        User roleChange = changeRole(createdUser, USER);
        return roleChange;
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(long userId, User updatedUser) {
        User user = userRepository.findById(userId).get();
        user.setUserName(updatedUser.getUserName());
        user.setPassword(updatedUser.getPassword());
        user.setRoles(updatedUser.getRoles());
        userRepository.save(user);
        return user;

    }

    public User changeRole(User user, String Role) {
        Roles role = roleRepository.findRoleByRoleName(Role);
        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        return userRepository.save(user);
    }

    @Override
    public User changeRoleToAdmin(User user) {
        return changeRole(user, ADMIN);
    }

    @Override
    public User changeRoleToUser(User user) {
        return changeRole(user, USER);
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User changeRoleToManager(User user) {
        return changeRole(user, MANAGER);
    }

    @Override
    public User changeRoleToCustomerService(User user) {
        return changeRole(user, CUSTOMER_SERVICE);
    }

    @Override
    public User changeRoleToCashier(User user) {
        return changeRole(user, CASHIER);
    }

}
