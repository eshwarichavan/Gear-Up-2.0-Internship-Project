package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.Tool;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool,Long> {

    long countByFactory(Factory factory);

    Long countByToolUsageAndFactory(ToolUsage toolUsage, Factory factory);
}
