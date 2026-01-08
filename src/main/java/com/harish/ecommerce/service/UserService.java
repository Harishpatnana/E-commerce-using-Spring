package com.harish.ecommerce.service;

import com.harish.ecommerce.dto.LoginRequestDTO;
import com.harish.ecommerce.dto.LoginResponseDTO;
import com.harish.ecommerce.dto.UserDTO;
import com.harish.ecommerce.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserDTO dto);
    UserResponseDTO getUserById(Long id);
	LoginResponseDTO login(LoginRequestDTO dto);
}
