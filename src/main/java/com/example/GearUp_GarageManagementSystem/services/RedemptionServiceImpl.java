//package com.example.GearUp_GarageManagementSystem.services;
//
//import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
//import com.example.GearUp_GarageManagementSystem.models.dtos.RedemptionRequestDTO;
//import com.example.GearUp_GarageManagementSystem.models.dtos.RedemptionResponseDTO;
//import com.example.GearUp_GarageManagementSystem.models.entities.Merchandise;
//import com.example.GearUp_GarageManagementSystem.models.entities.RedemptionHistory;
//import com.example.GearUp_GarageManagementSystem.models.entities.User;
//import com.example.GearUp_GarageManagementSystem.models.enums.RedemtionStatuses;
//import com.example.GearUp_GarageManagementSystem.repositories.MerchandiseRepository;
//import com.example.GearUp_GarageManagementSystem.repositories.RedemptionHistoryRepository;
//import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class RedemptionServiceImpl {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RedemptionHistoryRepository redemptionHistoryRepository;
//
//    @Autowired
//    private MerchandiseRepository merchandiseRepository;
//
//    public RedemptionResponseDTO addRedemption(RedemptionRequestDTO dto) {
//        User distributor = userRepository.findById(dto.getDistributorId())
//                .orElseThrow(() -> new CustomException("Distributor not found", HttpStatus.NOT_FOUND));
//
//        Merchandise item = merchandiseRepository.findById(dto.getMerchandiseId())
//                .orElseThrow(() -> new CustomException("Merchandise not found", HttpStatus.NOT_FOUND));
//
//        RedemptionHistory redemption = RedemptionHistory.builder()
//                .distributor(distributor)
//                .redeemedItem(item)
//                .pointsUsed(dto.getPointsUsed())
//                .status(RedemtionStatuses.valueOf(dto.getStatus() != null ? dto.getStatus() : "PENDING"))
//                .build();
//
//        RedemptionHistory saved = redemptionHistoryRepository.save(redemption);
//
//        return new RedemptionResponseDTO(
//                saved.getId(),
//                distributor.getFullName(),
//                distributor.getEmail(),
//                item.getItemName(),
//                saved.getPointsUsed(),
//                saved.getStatus(),
//                saved.getRedeemedAt(),
//                "Redemption record added successfully!"
//        );
//    }
//
//    // Get All Redemptions
//    public List<RedemptionResponseDTO> getAllRedemptions() {
//        return redemptionHistoryRepository.findAll().stream()
//                .map(r -> new RedemptionResponseDTO(
//                        r.getId(),
//                        r.getDistributor().getFullName(),
//                        r.getDistributor().getEmail(),
//                        r.getRedeemedItem().getItemName(),
//                        r.getPointsUsed(),
//                        r.getStatus(),
//                        r.getRedeemedAt(),
//                        "Fetched successfully"
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Search by Distributor Name
//    public List<RedemptionResponseDTO> searchByDistributor(String name) {
//        return redemptionHistoryRepository.findByDistributor_FullNameContainingIgnoreCase(name).stream()
//                .map(r -> new RedemptionResponseDTO(
//                        r.getId(),
//                        r.getDistributor().getFullName(),
//                        r.getDistributor().getEmail(),
//                        r.getRedeemedItem().getItemName(),
//                        r.getPointsUsed(),
//                        r.getStatus(),
//                        r.getRedeemedAt(),
//                        "Fetched successfully"
//                ))
//                .collect(Collectors.toList());
//    }
//
//    // Update Redemption Status
//    public RedemptionResponseDTO updateStatus(Long id, String status) {
//        RedemptionHistory redemption = redemptionHistoryRepository.findById(id)
//                .orElseThrow(() -> new CustomException("Redemption record not found", HttpStatus.NOT_FOUND));
//
//        redemption.setStatus(RedemtionStatuses.valueOf(status));
//        RedemptionHistory updated = redemptionHistoryRepository.save(redemption);
//
//        return new RedemptionResponseDTO(
//                updated.getId(),
//                updated.getDistributor().getFullName(),
//                updated.getDistributor().getEmail(),
//                updated.getRedeemedItem().getItemName(),
//                updated.getPointsUsed(),
//                updated.getStatus(),
//                updated.getRedeemedAt(),
//                "Redemption status updated!"
//        );
//    }
//}
//
//
