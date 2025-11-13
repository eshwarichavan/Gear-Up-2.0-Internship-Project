package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseResponseDTO;

import java.util.List;

public interface MerchandiseService {

    MerchandiseResponseDTO addMerchandise(MerchandiseRequestDTO dto);
    List<MerchandiseResponseDTO> getAllMerchandise();
    List<MerchandiseResponseDTO> searchByName(String name);
    MerchandiseResponseDTO updateMerchandise(Long id, MerchandiseRequestDTO dto);
    String deleteMerchandise(Long id);
}
