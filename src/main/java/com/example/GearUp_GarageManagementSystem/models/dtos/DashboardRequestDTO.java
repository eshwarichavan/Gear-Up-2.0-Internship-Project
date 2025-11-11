package com.example.GearUp_GarageManagementSystem.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardRequestDTO {

    private Long totalFactories;
    private Long frequentToolsCount;
    private Long rareToolsCount;
}
