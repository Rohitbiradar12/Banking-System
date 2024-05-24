package com.bankingApp.Banking.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Entity.Roles;
import com.bankingApp.Banking.app.Entity.User;
import com.bankingApp.Banking.app.Mapper.UserMapper;
import com.bankingApp.Banking.app.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    private UserMapper userMapper=new UserMapper();
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User user=userMapper.mapToUser(userDto);
        User createdUser= userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers=userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user=userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/role/{id}")
    public ResponseEntity<String> getUserRole(@PathVariable long id){
        User user=userService.getUser(id);
        Roles role=user.getRoles().get(0);
        String roleName=role.getRoleName();
        return ResponseEntity.ok(roleName);
    }
}
