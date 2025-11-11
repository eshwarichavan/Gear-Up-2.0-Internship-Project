package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.UserRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO dto);

    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);

    UserResponseDTO getUserById(Long id);

    List<UserResponseDTO> getAllUsers();

}
