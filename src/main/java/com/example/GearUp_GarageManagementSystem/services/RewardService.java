package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.RewardsResponseDTO;

import java.util.List;

public interface RewardService {

    List<RewardsResponseDTO> getAllRewards();
    List<RewardsResponseDTO> searchByDistributorName(String name);
    RewardsResponseDTO updateRewardPoints(Long id, Long newPoints);
    String deleteRewardRecord(Long id);
}
