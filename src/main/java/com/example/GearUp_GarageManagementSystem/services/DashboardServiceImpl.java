package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.models.dtos.DashboardResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolUsage;
import com.example.GearUp_GarageManagementSystem.repositories.FactoryRepository;
import com.example.GearUp_GarageManagementSystem.repositories.ProductRepository;
import com.example.GearUp_GarageManagementSystem.repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashBoardService {

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public  List<DashboardResponseDTO> getFactoryPerformance() {

        List<Factory> factories = factoryRepository.findAll();

        return factories.stream().map(factory -> {

            // Total production output (e.g., total products under this factory)
            Long productionCount = productRepository.countByFactory(factory);

            // Target count from DB (if null, use 0L)
            Long targetCount = factory.getTargetProductionCount() != null
                    ? factory.getTargetProductionCount() : 0L;

            // Tools count for this factory
            Long totalTools = toolRepository.countByFactory(factory);
            Long frequentlyUsed = toolRepository.countByToolUsageAndFactory(ToolUsage.FREQUENTLY_USED, factory);
            Long rarelyUsed = toolRepository.countByToolUsageAndFactory(ToolUsage.RARELY_USED, factory);


            // Calculate percentages safely
            double performancePercent = targetCount == 0 ? 0 : (productionCount * 100.0) / targetCount;
            double frequentToolPercent = totalTools == 0 ? 0 : (frequentlyUsed * 100.0) / totalTools;
            double rareToolPercent = totalTools == 0 ? 0 : (rarelyUsed * 100.0) / totalTools;

            return DashboardResponseDTO.builder()
                    .factoryName(factory.getFactoryName())
                    .productionCount(productionCount)
                    .targetCount(targetCount)
                    .performancePercentage(performancePercent)
                    .frequentToolPercentage(frequentToolPercent)
                    .rareToolPercentage(rareToolPercent)
                    .build();

        }).collect(Collectors.toList());
    }
}

