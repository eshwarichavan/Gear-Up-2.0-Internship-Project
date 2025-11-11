package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FactoryService {

    FactoryResponseDTO createFactory(FactoryRequestDTO dto);

    FactoryResponseDTO updateFactory(Long id, FactoryRequestDTO dto);

    void deleteFactory(Long id);

    FactoryResponseDTO getFactoryById(Long id);

    List<FactoryResponseDTO> getAllFactories();

    Page<FactoryResponseDTO> getFactoriesByPage(int page, int size);

    List<Factory> searchFactoriesByName(String name);
}