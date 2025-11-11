package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FactoryRepository extends JpaRepository<Factory,Long> {


    //Optional<Factory> findByFactoryName(String factoryName);

    Optional<Factory> findByFactoryNameIgnoreCase(String factoryName);

    List<Factory> findByFactoryNameContainingIgnoreCase(String name);
}
