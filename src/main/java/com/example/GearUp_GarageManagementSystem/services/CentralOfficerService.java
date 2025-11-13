package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.CentralOfficerRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.CentralOfficerResponseDTO;

import java.util.List;

public interface CentralOfficerService {

    CentralOfficerResponseDTO addOfficer(CentralOfficerRequestDTO dto);
    List<CentralOfficerResponseDTO> getAllOfficers();
    List<CentralOfficerResponseDTO> searchOfficersByName(String name);
    CentralOfficerResponseDTO updateOfficer(Long id, CentralOfficerRequestDTO dto);
    String deleteOfficer(Long id);
}
