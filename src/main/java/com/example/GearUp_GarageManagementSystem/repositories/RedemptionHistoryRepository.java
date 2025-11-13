package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.RedemptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface RedemptionHistoryRepository extends JpaRepository<RedemptionHistory,Long> {
    List<RedemptionHistory> findByDistributor_FullNameContainingIgnoreCase(String name);
}
