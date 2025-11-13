package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.entities.Merchandise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardsResponseDTO {

    private Long id;
    private String distributorName;
    private String distributorEmail;
    private Long totalPoints;
    private Merchandise redeemedItem;
    private Long pointsUsed;
    private boolean itemShipped;
    private String message;


}
