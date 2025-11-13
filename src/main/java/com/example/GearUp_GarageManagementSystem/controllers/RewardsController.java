package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.RewardsRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.RewardsResponseDTO;
import com.example.GearUp_GarageManagementSystem.services.RewardService;
import com.example.GearUp_GarageManagementSystem.services.RewardServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Member API", description = "Central Officer Rewards Management of the members")
@CrossOrigin(origins = {"http://localhost:3000"})
public class RewardsController {


    @Autowired
    private RewardServiceImpl rewardService;

    @GetMapping("/getAll")
    @Operation(summary = "Get all reward records")
    public ResponseEntity<List<RewardsResponseDTO>> getAll() {
        return ResponseEntity.ok(rewardService.getAllRewards());
    }

    @GetMapping("/search")
    @Operation(summary = "Search rewards by distributor name")
    public ResponseEntity<List<RewardsResponseDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok(rewardService.searchByDistributorName(name));
    }

    @PutMapping("/updatePoints/{id}")
    @Operation(summary = "Update reward points manually")
    public ResponseEntity<RewardsResponseDTO> updatePoints(@PathVariable Long id, @RequestParam Long points) {
        return ResponseEntity.ok(rewardService.updateRewardPoints(id, points));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete reward record")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(rewardService.deleteRewardRecord(id));
    }
}
