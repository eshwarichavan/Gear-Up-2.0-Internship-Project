package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CentralOfficerResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String userCity;
    private String userAddress;
    private String profileImageURL;
    private Roles role;
    private String centralOfficeName;
    private String message;

}