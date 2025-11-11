package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.StaffRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.StaffResponseDTO;

import java.util.List;

public interface StaffService {

    StaffResponseDTO addStaff(StaffRequestDTO dto);
    List<StaffResponseDTO> getAllStaff();
    List<StaffResponseDTO> searchStaffByName(String name);
    List<StaffResponseDTO> getStaffByRole(String role);
    StaffResponseDTO updateStaff(Long id, StaffRequestDTO dto);
    String deleteStaff(Long id);

}
