package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {

    //private Long id;
    private String fullName;
    private String email;
    private String role;
}
