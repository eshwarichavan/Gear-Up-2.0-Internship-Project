package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.RewardsResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Rewards;
import com.example.GearUp_GarageManagementSystem.repositories.RewardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardServiceImpl implements RewardService{

    @Autowired
    private RewardsRepository rewardRepository;

    @Override
    public List<RewardsResponseDTO> getAllRewards() {
        return rewardRepository.findAll().stream()

                .filter(r -> r.getUser() != null &&
                        r.getUser().getRoles().toString().equalsIgnoreCase("DISTRIBUTOR"))

                .map(r -> new RewardsResponseDTO(
                        r.getId(),
                        r.getUser().getFullName(),
                        r.getUser().getEmail(),
                        r.getTotalPoints(),
                        r.getRedeemedItem(),
                        r.getPointsUsed(),
                        r.isItemShipped(),
                        "Fetched Successfully"
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RewardsResponseDTO> searchByDistributorName(String name) {
        return rewardRepository.findByDistributorFullNameContainingIgnoreCase(name).stream()
                .filter(r -> r.getUser() != null && r.getUser().getRoles().name().equalsIgnoreCase("DISTRIBUTOR"))
                .map(r -> new RewardsResponseDTO(
                        r.getId(),
                        r.getUser().getFullName(),
                        r.getUser().getEmail(),
                        r.getTotalPoints(),
                        r.getRedeemedItem(),
                        r.getPointsUsed(),
                        r.isItemShipped(),
                        null))
                .collect(Collectors.toList());
    }

    @Override
    public RewardsResponseDTO updateRewardPoints(Long id, Long newPoints) {
        Rewards reward = rewardRepository.findById(id)
                .orElseThrow(() -> new CustomException("Member not found", HttpStatus.NOT_FOUND));

        reward.setTotalPoints(newPoints);
        Rewards updated = rewardRepository.save(reward);

        return new RewardsResponseDTO(
                updated.getId(),
                updated.getUser().getFullName(),
                updated.getUser().getEmail(),
                updated.getTotalPoints(),
                updated.getRedeemedItem(),
                updated.getPointsUsed(),
                updated.isItemShipped(),
                "Reward points updated successfully");
    }

    @Override
    public String deleteRewardRecord(Long id) {
        if (!rewardRepository.existsById(id)) {
            throw new CustomException("Member record not found", HttpStatus.NOT_FOUND);
        }
        rewardRepository.deleteById(id);
        return "Member deleted successfully";
    }

}
