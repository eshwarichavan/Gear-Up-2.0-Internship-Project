package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantHeadResponseDTO {

    private String message;
    private String managerName;
    private String managerEmail;
    private String factoryName;
    private String city;
}
