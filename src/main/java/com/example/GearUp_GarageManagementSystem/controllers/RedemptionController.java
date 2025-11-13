package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.RedemptionRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.RedemptionResponseDTO;
import com.example.GearUp_GarageManagementSystem.services.RedemptionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/redemptions")
@Tag(name = "Redemption History", description = "Manage reward redemptions by distributors")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RedemptionController {


        @Autowired
        private RedemptionServiceImpl redemptionService;

        @PostMapping("/add")
        @Operation(summary = "Add a new redemption record")
        public ResponseEntity<RedemptionResponseDTO> addRedemption(@Valid @RequestBody RedemptionRequestDTO dto) {
            return ResponseEntity.ok(redemptionService.addRedemption(dto));
        }

        @GetMapping("/getAll")
        @Operation(summary = "Fetch all redemption records")
        public ResponseEntity<List<RedemptionResponseDTO>> getAll() {
            return ResponseEntity.ok(redemptionService.getAllRedemptions());
        }

        @GetMapping("/search")
        @Operation(summary = "Search redemption history by distributor name")
        public ResponseEntity<List<RedemptionResponseDTO>> search(@RequestParam String name) {
            return ResponseEntity.ok(redemptionService.searchByDistributor(name));
        }

        @PutMapping("/updateStatus/{id}")
        @Operation(summary = "Update redemption status (PENDING/APPROVED/SHIPPED/CANCELLED)")
        public ResponseEntity<RedemptionResponseDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
            return ResponseEntity.ok(redemptionService.updateStatus(id, status));
        }

    }
