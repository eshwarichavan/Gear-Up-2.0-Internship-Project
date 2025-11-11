package com.example.GearUp_GarageManagementSystem.repositories;

import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    List<Product> findByFactoryId(Long factoryId);
//    List<Product> findByCategory(String category);
//    List<Product> findByIsAvailable(Boolean isAvailable);

    // For filtering by category / availability / factory with pagination
    Page<Product> findByCategoryIgnoreCaseAndIsAvailableAndFactoryFactoryId(
            String category, Boolean isAvailable, Long factoryId, Pageable pageable);

    long countByFactory(Factory factory);

}
