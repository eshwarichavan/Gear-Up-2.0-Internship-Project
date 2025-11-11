package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.ToolRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.ToolResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.Tool;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolCategory;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
import com.example.GearUp_GarageManagementSystem.repositories.FactoryRepository;
import com.example.GearUp_GarageManagementSystem.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToolServiceImpl {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private FactoryRepository factoryRepository;


    // Add Tools :
    public ToolResponseDTO addTool(ToolRequestDTO dto){

        Factory factory=factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(()-> new CustomException("Factory Not Found", HttpStatus.NOT_FOUND));

        Tool tool= Tool.builder()
                .toolName(dto.getToolName())
                .toolType(dto.getToolType())
                .toolCategory(ToolCategory.valueOf(dto.getToolCategory().toUpperCase()))
                .toolNature(dto.getToolNature())
                .quantity(dto.getQuantity())
                .factory(factory)
                .build();

        Tool savedTool=toolRepository.save(tool);


        return new ToolResponseDTO(
                savedTool.getToolId(),
                "Tools added Successfully !",
                savedTool.getToolName(),
                savedTool.getToolType(),
                savedTool.getToolCategory().name(),
                savedTool.getQuantity(),
                savedTool.getToolNature(),
                savedTool.getFactory().getFactoryName()
        );
    }

    // Read by id
    public Optional<Tool> getToolById(Long id) {
        return toolRepository.findById(id);
    }


    // Get tools with filtering + pagination + sorting :
    public Page<ToolResponseDTO> getAllTools(String category, ToolNature nature, Long factoryId,
                                             int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Tool> toolsPage = toolRepository.findAll(pageable);

        // Filter
        List<Tool> filtered = toolsPage.getContent().stream()
                .filter(t -> (category == null || t.getToolCategory().name().equalsIgnoreCase(category)))
                .filter(t -> (nature == null || t.getToolNature() == nature))
                .filter(t -> (factoryId == null || t.getFactory().getFactoryId().equals(factoryId)))
                .collect(Collectors.toList());

        List<ToolResponseDTO> response = filtered.stream()
                .map(t -> new ToolResponseDTO(
                        t.getToolId(),
                        null,
                        t.getToolName(),
                        t.getToolType(),
                        t.getToolCategory().name(),
                        t.getQuantity(),
                        t.getToolNature(),
                        t.getFactory().getFactoryName()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(response, pageable, toolsPage.getTotalElements());
    }



    // Update tool :
    public ToolResponseDTO updateTool(Long id, ToolRequestDTO request) {
        Tool tool = toolRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tool not found",HttpStatus.NOT_FOUND));

        if (request.getToolName() != null)
            tool.setToolName(request.getToolName());

        if (request.getToolType() != null)
            tool.setToolType(request.getToolType());

        if (request.getToolCategory() != null)
            tool.setToolCategory(ToolCategory.valueOf(request.getToolCategory().toUpperCase()));

        if (request.getToolNature() != null)
            tool.setToolNature(request.getToolNature());

        if (request.getQuantity() != null)
            tool.setQuantity(request.getQuantity());

        if (request.getFactoryId() != null) {
            Factory factory = factoryRepository.findById(request.getFactoryId())
                    .orElseThrow(() -> new CustomException("Factory not found",HttpStatus.NOT_FOUND));
            tool.setFactory(factory);
        }

        Tool updated = toolRepository.save(tool);

        return new ToolResponseDTO(
                updated.getToolId(),
                "Tool updated successfully!",
                updated.getToolName(),
                updated.getToolType(),
                updated.getToolCategory().name(),
                updated.getQuantity(),
                updated.getToolNature(),
                updated.getFactory().getFactoryName()
        );
    }



    // Delete Tools :
    public String deleteTool(Long id){
        if(!toolRepository.existsById(id)) {
            throw new CustomException("Tools Not Found !",HttpStatus.NOT_FOUND);
        }

        toolRepository.deleteById(id);
        return "Tool Deleted Successfully !";
    }


}
