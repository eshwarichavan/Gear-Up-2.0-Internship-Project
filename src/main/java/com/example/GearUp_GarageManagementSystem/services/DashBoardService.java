package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.DashboardRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.DashboardResponseDTO;

import java.util.List;

public interface DashBoardService {

    List<DashboardResponseDTO> getFactoryPerformance();
}
