package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.ToolRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.ToolResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Tool;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
import com.example.GearUp_GarageManagementSystem.services.ToolServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tools")
@Tag(name="Tool API")
@Validated
public class ToolController {

    @Autowired
    private ToolServiceImpl toolService;

    @PostMapping("/add")
    @Operation(summary = "Tools will be added")
    public ResponseEntity<ToolResponseDTO> addTool(
            @Valid
            @RequestBody ToolRequestDTO request) {
        return ResponseEntity.ok(toolService.addTool(request));
    }


    @GetMapping("/getAll")
    @Operation(summary = "Get all Tools")
    public ResponseEntity<Page<ToolResponseDTO>> getAllTools(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) ToolNature nature,
            @RequestParam(required = false) Long factoryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "toolName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(toolService.getAllTools(category, nature, factoryId, page, size, sortBy, direction));
    }


    @GetMapping("/getById/{id}")
    @Operation(summary = "Get Tool by Id")
    public ResponseEntity<Tool> getToolById(@PathVariable Long id) {
        return toolService.getToolById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/update/{id}")
    @Operation(summary = "Get Tools updated by Id")
    public ResponseEntity<ToolResponseDTO> updateTool(
            @Valid
            @PathVariable Long id,
            @RequestBody ToolRequestDTO request) {

        return ResponseEntity.ok(toolService.updateTool(id, request));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Get Tools delete by Id")
    public ResponseEntity<String> deleteTool(@PathVariable Long id) {
        return ResponseEntity.ok(toolService.deleteTool(id));
    }
}
