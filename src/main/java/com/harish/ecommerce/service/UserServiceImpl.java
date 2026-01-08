package com.harish.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.harish.ecommerce.dto.LoginRequestDTO;
import com.harish.ecommerce.dto.LoginResponseDTO;
import com.harish.ecommerce.dto.UserDTO;
import com.harish.ecommerce.dto.UserResponseDTO;
import com.harish.ecommerce.model.Users;
import com.harish.ecommerce.repo.UserRepo;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    
    

    @Override
    public UserResponseDTO createUser(UserDTO dto) {

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole()); 
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
// ✅ FIX

        Users saved = userRepo.save(user);
        return userResponseDTO(saved);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Users user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userResponseDTO(user);
    }

    private UserDTO mapToDTO(Users user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail()); // ✅ FIX
        dto.setRole(user.getRole());
        return dto;
    }
    
    private UserResponseDTO userResponseDTO(Users us) {
    	UserResponseDTO dto=new UserResponseDTO();
    	dto.setEmail(us.getEmail());
    	dto.setRole(us.getRole());
    	dto.setUsername(us.getUsername());
    	return dto;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO dto) {
        Users user = userRepo.findByUsername(dto.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setMessage("Login Successful");
        return response;
    }

}

