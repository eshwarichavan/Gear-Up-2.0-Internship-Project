package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.DistributorRegisterRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.SignInRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AuthService {

    String signup(DistributorRegisterRequestDTO requestDTO);
    String signin(SignInRequestDTO requestDTO);


}
