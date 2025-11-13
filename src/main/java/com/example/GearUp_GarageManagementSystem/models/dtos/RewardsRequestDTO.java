package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardsRequestDTO {

    private Long distributorId;
    private String rewardName;
    private String rewardDescription;
    private Long rewardPoints;
}
