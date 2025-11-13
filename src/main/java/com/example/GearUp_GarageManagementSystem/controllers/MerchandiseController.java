package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.MerchandiseResponseDTO;
import com.example.GearUp_GarageManagementSystem.services.MerchandiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchandise")
@Tag(name = "Merchandise API", description = "Central Officer Merchandise Management")
@CrossOrigin(origins = {"http://localhost:3000"})
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;

    @PostMapping("/add")
    @Operation(summary = "Add new merchandise")
    public ResponseEntity<MerchandiseResponseDTO> add(@Valid @RequestBody MerchandiseRequestDTO dto) {
        return ResponseEntity.ok(merchandiseService.addMerchandise(dto));
    }

    @GetMapping("/getAll")
    @Operation(summary = "get all merchandise")
    public ResponseEntity<List<MerchandiseResponseDTO>> getAll() {
        return ResponseEntity.ok(merchandiseService.getAllMerchandise());
    }

    @GetMapping("/search")
    @Operation(summary = "searches merchandise")
    public ResponseEntity<List<MerchandiseResponseDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok(merchandiseService.searchByName(name));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "update merchandise")
    public ResponseEntity<MerchandiseResponseDTO> update(@PathVariable Long id, @Valid @RequestBody MerchandiseRequestDTO dto) {
        return ResponseEntity.ok(merchandiseService.updateMerchandise(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete new merchandise")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(merchandiseService.deleteMerchandise(id));
    }
}