package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Rewards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface RewardsRepository extends JpaRepository<Rewards,Long> {

    List<Rewards> findByDistributorFullNameContainingIgnoreCase(String fullName);
}
