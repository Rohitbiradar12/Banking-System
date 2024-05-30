package com.bankingApp.Banking.app.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.Roles;
import com.bankingApp.Banking.app.Entity.User;
import com.bankingApp.Banking.app.Mapper.RoleMapper;
import com.bankingApp.Banking.app.Mapper.UserMapper;
import com.bankingApp.Banking.app.Repository.RoleRepository;
import com.bankingApp.Banking.app.Repository.UserRepository;

import jakarta.annotation.PostConstruct;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Roles role = roleRepository.findRoleByRoleName(USER);
            if (role == null) {
                throw new RuntimeException("Default role USER not found");
            }
            user.setRoles(Collections.singletonList(role));
        }
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(userDto.getUserName());
            existingUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
            existingUser.setRoles(userDto.getRolesDto().stream()
                .map(roleDto -> {
                    Roles role = roleMapper.mapToRole(roleDto);
                    if (role == null) {
                        throw new RuntimeException("Role not found: " + roleDto.getRoleName());
                    }
                    return role;
                })
                .collect(Collectors.toList()));
            User updatedUser = userRepository.save(existingUser);
            return userMapper.mapToUserDto(updatedUser);
        }
        return null; // or throw an exception if user not found
    }

    public UserDto changeRole(UserDto userDto, String roleName) {
        Roles role = roleRepository.findRoleByRoleName(roleName);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleName);
        }
        User user = userMapper.mapToUser(userDto);
        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto changeRoleToAdmin(UserDto userDto) {
        return changeRole(userDto, ADMIN);
    }

    @Override
    public UserDto getUserById(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null ? userMapper.mapToUserDto(user) : null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
            .map(userMapper::mapToUserDto)
            .collect(Collectors.toList());
    }

    @Override
    public UserDto changeRoleToManager(UserDto userDto) {
        return changeRole(userDto, MANAGER);
    }

    @Override
    public UserDto changeRoleToCustomerService(UserDto userDto) {
        return changeRole(userDto, CUSTOMER_SERVICE);
    }

    @Override
    public UserDto changeRoleToUser(UserDto userDto) {
        return changeRole(userDto, USER);
    }

    @Override
    public UserDto changeRoleToCashier(UserDto userDto) {
        return changeRole(userDto, CASHIER);
    }

    @Override
    public List<String> getUserRole(long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getRoles().stream()
                .map(Roles::getRoleName)
                .collect(Collectors.toList());
        } else {
            return Collections.emptyList(); // or throw an exception
        }
    }

    @PostConstruct
    public void initAdminUser() {
        initRoles(); // Initialize roles if not present

        String adminUserName = "admin";
        String adminPassword = "admin@123";

        User existingAdmin = userRepository.findByUserName(adminUserName);
        if (existingAdmin == null) {
            User adminUser = new User();
            adminUser.setUserName(adminUserName);
            adminUser.setPassword(passwordEncoder.encode(adminPassword)); // Encode the password
            adminUser.setEnabled(true);
            Roles adminRole = roleRepository.findRoleByRoleName(ADMIN);
            if (adminRole == null) {
                throw new RuntimeException("Role not found: " + ADMIN);
            }
            adminUser.setRoles(Collections.singletonList(adminRole));
            userRepository.save(adminUser);
        }
    }

    private void initRoles() {
        String[] roles = {ADMIN, CUSTOMER_SERVICE, MANAGER, USER, CASHIER};
        for (String role : roles) {
            if (roleRepository.findRoleByRoleName(role) == null) {
                Roles newRole = new Roles();
                newRole.setRoleName(role);
                roleRepository.save(newRole);
            }
        }
    }
}
