package com.harish.ecommerce.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harish.ecommerce.dto.LoginRequestDTO;
import com.harish.ecommerce.dto.LoginResponseDTO;
import com.harish.ecommerce.dto.UserDTO;
import com.harish.ecommerce.dto.UserResponseDTO;
import com.harish.ecommerce.service.UserService;
import com.harish.ecommerce.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public UserResponseDTO createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {
    	return userService.login(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
