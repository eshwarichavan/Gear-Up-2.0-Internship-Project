package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Staff;
import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findByFullNameContainingIgnoreCase(String fullName);
    List<Staff> findByRole(Roles role);
}
