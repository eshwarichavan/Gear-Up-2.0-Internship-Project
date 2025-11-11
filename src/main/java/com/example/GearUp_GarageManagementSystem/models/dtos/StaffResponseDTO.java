package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private String userCity;
    private String userAddress;
    private String assignedFactory;
    private String assignedBay;
    private String profileImageURL;
    private Roles role;
}
