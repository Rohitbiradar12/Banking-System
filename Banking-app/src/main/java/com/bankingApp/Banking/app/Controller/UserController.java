package com.bankingApp.Banking.app.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingApp.Banking.app.DTO.LoginRequestDto;
import com.bankingApp.Banking.app.DTO.UserDto;
import com.bankingApp.Banking.app.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    } catch (AuthenticationException e) {
        return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
    }
}


 

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<String>> getUserRole(@PathVariable long id) {
        List<String> roles = userService.getUserRole(id);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return null;
        
    }

    // @GetMapping
    // public ResponseEntity<String> successHandler(){
    //     return ResponseEntity.ok("success");
    // }


    @GetMapping
    public ResponseEntity<String> successHandler(){
        return ResponseEntity.ok("success");
    }
}
