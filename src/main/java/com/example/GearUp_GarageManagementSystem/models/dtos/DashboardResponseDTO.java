package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponseDTO {

    private String factoryName;
    private Long productionCount;
    private Long targetCount;
    private Double performancePercentage;
    private Double frequentToolPercentage;
    private Double rareToolPercentage;
}
