package com.example.GearUp_GarageManagementSystem.models.entities;

import com.example.GearUp_GarageManagementSystem.models.enums.RedemtionStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="redemption_history")
@Builder
public class RedemptionHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id")
    private User distributor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchandise_id", nullable = false)
    private Merchandise redeemedItem;


    @Column(name = "points_used",nullable = false)
    private Long pointsUsed;

    @Column(name = "status",nullable = false)
    private RedemtionStatuses status;

    @Column(name = "redeemed_at")
    private LocalDateTime redeemedAt=LocalDateTime.now();
}
