package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.DashboardResponseDTO;
import com.example.GearUp_GarageManagementSystem.services.DashboardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owner/dashboard")
@Tag(name="Dashboard API",description = "This Dashboard Controller will show performance of the factory")
public class DashboardController {

    @Autowired
    private DashboardServiceImpl dashboardService;

    @GetMapping("/performance")
    @Operation(summary = "Performance of the dashboard will be shown")
    public ResponseEntity<List<DashboardResponseDTO>> getFactoryPerformance() {
        return ResponseEntity.ok(dashboardService.getFactoryPerformance());
    }
}
