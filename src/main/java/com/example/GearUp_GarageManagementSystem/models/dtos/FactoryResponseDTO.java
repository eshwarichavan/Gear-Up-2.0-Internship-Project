package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactoryResponseDTO {

    private String message; //factory created msg
    private Long id;
    private String factoryName;
    private String city;
    private String address;


}
