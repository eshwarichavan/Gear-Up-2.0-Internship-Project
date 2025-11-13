package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Merchandise;
import com.example.GearUp_GarageManagementSystem.repositories.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchandiseServiceImpl implements  MerchandiseService{


    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Override
    public MerchandiseResponseDTO addMerchandise(MerchandiseRequestDTO dto) {
        Merchandise merchandise = new Merchandise();
        merchandise.setItemName(dto.getItemName());
        merchandise.setItemDescription(dto.getItemDescription());
        merchandise.setPricePoints(dto.getPricePoints());
        merchandise.setInStock(dto.isInStock());

        Merchandise saved = merchandiseRepository.save(merchandise);

        return new MerchandiseResponseDTO(
                saved.getId(),
                saved.getItemName(),
                saved.getItemDescription(),
                saved.getPricePoints(),
                saved.isInStock(),
                "Merchandise added successfully"
        );
    }

    @Override
    public List<MerchandiseResponseDTO> getAllMerchandise() {
        return merchandiseRepository.findAll().stream()
                .map(m -> new MerchandiseResponseDTO(
                        m.getId(), m.getItemName(), m.getItemDescription(),
                        m.getPricePoints(), m.isInStock(), null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<MerchandiseResponseDTO> searchByName(String name) {
        return merchandiseRepository.findByItemNameContainingIgnoreCase(name).stream()
                .map(m -> new MerchandiseResponseDTO(
                        m.getId(), m.getItemName(), m.getItemDescription(),
                        m.getPricePoints(), m.isInStock(), null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public MerchandiseResponseDTO updateMerchandise(Long id, MerchandiseRequestDTO dto) {
        Merchandise existing = merchandiseRepository.findById(id)
                .orElseThrow(() -> new CustomException("Merchandise not found", HttpStatus.NOT_FOUND));

        existing.setItemName(dto.getItemName());
        existing.setItemDescription(dto.getItemDescription());
        existing.setPricePoints(dto.getPricePoints());
        existing.setInStock(dto.isInStock());

        Merchandise updated = merchandiseRepository.save(existing);

        return new MerchandiseResponseDTO(
                updated.getId(),
                updated.getItemName(),
                updated.getItemDescription(),
                updated.getPricePoints(),
                updated.isInStock(),
                "Merchandise updated successfully"
        );
    }

    @Override
    public String deleteMerchandise(Long id) {
        if (!merchandiseRepository.existsById(id)) {
            throw new CustomException("Merchandise not found", HttpStatus.NOT_FOUND);
        }
        merchandiseRepository.deleteById(id);
        return "Merchandise deleted successfully";
    }

}
