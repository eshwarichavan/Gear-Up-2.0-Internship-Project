package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MerchandiseRepository extends JpaRepository<Merchandise,Long> {

    List<Merchandise> findByItemNameContainingIgnoreCase(String itemName);

}
