package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.enums.RedemtionStatuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedemptionResponseDTO {

    private Long id;
    private String distributorName;
    private String distributorEmail;
    private String redeemedItemName;
    private Long pointsUsed;
    private RedemtionStatuses status;
    private LocalDateTime redeemedAt;
    private String message;
}
