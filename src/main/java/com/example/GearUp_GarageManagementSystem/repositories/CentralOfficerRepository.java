package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralOfficerRepository extends JpaRepository<User,Long> {
}
