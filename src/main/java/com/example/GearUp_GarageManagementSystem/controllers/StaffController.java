package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.StaffRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.StaffResponseDTO;
import com.example.GearUp_GarageManagementSystem.services.StaffServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@Tag(name="Staff API")
public class StaffController {

    @Autowired
    private StaffServiceImpl staffService;


    @PostMapping("/add")
    @Operation(summary = "Add a new staff member")
    public ResponseEntity<StaffResponseDTO> addStaff(@Valid @RequestBody StaffRequestDTO dto) {
        return ResponseEntity.ok(staffService.addStaff(dto));
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all staff members across factories")
    public ResponseEntity<List<StaffResponseDTO>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaff());
    }

    @GetMapping("/search")
    @Operation(summary = "Search staff by name")
    public ResponseEntity<List<StaffResponseDTO>> searchStaff(@RequestParam String name) {
        return ResponseEntity.ok(staffService.searchStaffByName(name));
    }


    @GetMapping("/getByRole")
    @Operation(summary = "get staff name by role")
    public ResponseEntity<?> getStaffByRole(@RequestParam String role) {
        List<StaffResponseDTO> staffList = staffService.getStaffByRole(role);
        if (staffList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No staff found for role: " + role);
        }
        return ResponseEntity.ok(staffList);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "update staff details by id")
    public ResponseEntity<StaffResponseDTO> updateStaff(
            @PathVariable Long id,
            @RequestBody StaffRequestDTO dto) {
        return ResponseEntity.ok(staffService.updateStaff(id, dto));
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete staff by id")
    public ResponseEntity<String> deleteStaff(@PathVariable Long id) {
        return ResponseEntity.ok(staffService.deleteStaff(id));
    }

}


