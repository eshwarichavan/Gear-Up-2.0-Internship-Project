package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.UserSummaryDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.User;
import com.example.GearUp_GarageManagementSystem.repositories.CentralOfficeRepository;
import com.example.GearUp_GarageManagementSystem.repositories.FactoryRepository;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CentralOfficeRepository centralOfficeRepository;

    //Create Factory :
    public FactoryResponseDTO createFactory(FactoryRequestDTO dto){


        Factory factory = new Factory();
        factory.setFactoryName(dto.getFactoryName());
        factory.setCity(dto.getCity());
        factory.setAddress(dto.getAddress());

        // Save to DB
        Factory savedFactory = factoryRepository.save(factory);

        // Convert Entity → Response DTO
        FactoryResponseDTO responseDTO = new FactoryResponseDTO();
        responseDTO.setMessage("Factory created successfully!");
        responseDTO.setFactoryName(savedFactory.getFactoryName());
        responseDTO.setCity(savedFactory.getCity());
        responseDTO.setAddress(savedFactory.getAddress());

        return responseDTO;
    }



    //update Factory :
    @Override
    public FactoryResponseDTO updateFactory(Long id, FactoryRequestDTO dto) {


            // Find the factory by ID or throw an error if not found
            Factory factory = factoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factory not found with ID: " + id));

            // Update only if the new data is provided (prevents null overwrite)
            if (dto.getFactoryName() != null) {
                factory.setFactoryName(dto.getFactoryName());
            }
            if (dto.getCity() != null) {
                factory.setCity(dto.getCity());
            }
            if (dto.getAddress() != null) {
                factory.setAddress(dto.getAddress());
            }

            // Save the updated factory
            factoryRepository.save(factory);

            // Return response
            return new FactoryResponseDTO(
                    "Factory updated successfully!",
                    factory.getFactoryId(),
                    factory.getFactoryName(),
                    factory.getCity(),
                    factory.getAddress()
            );
    }




    //delete factory :
    @Override
    public void deleteFactory(Long id) {
        if(!factoryRepository.existsById(id)){
            throw new CustomException("Factory Not Found ",HttpStatus.NOT_FOUND);
        }
        factoryRepository.deleteById(id);
    }



    @Override
    public FactoryResponseDTO getFactoryById(Long id) {

        Factory factory = factoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Factory Not Found", HttpStatus.NOT_FOUND));

        return
                new FactoryResponseDTO(
                "Factory details fetched successfully!",
                        factory.getFactoryId(),
                factory.getFactoryName(),
                factory.getCity(),
                factory.getAddress()
        );
    }

    public List<Factory> searchFactoriesByName(String name) {
        return factoryRepository.findByFactoryNameContainingIgnoreCase(name);
    }



    @Override
    public List<FactoryResponseDTO> getAllFactories() {
        return factoryRepository.findAll().stream()
                .map(factory -> new FactoryResponseDTO(
                        "Factory listed successfully!",
                        factory.getFactoryId(),
                        factory.getFactoryName(),
                        factory.getCity(),
                        factory.getAddress()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Page<FactoryResponseDTO> getFactoriesByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return factoryRepository.findAll(pageable)
                .map(factory -> new FactoryResponseDTO(
                        "Factory listed successfully!",
                        factory.getFactoryId(),
                        factory.getFactoryName(),
                        factory.getCity(),
                        factory.getAddress()
                ));
    }

}




