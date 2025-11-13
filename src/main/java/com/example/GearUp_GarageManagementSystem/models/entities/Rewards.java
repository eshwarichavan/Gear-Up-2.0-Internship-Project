package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distributor_id", nullable = false)
    private User distributor;

    @Column(name = "distributor_email")
    private String distributorEmail;

    @Column(name = "total_points")
    private Long totalPoints;

    // For redemption history
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchandise_id", nullable = false)
    private Merchandise redeemedItem;

    @Column(name = "points_used")
    private Long pointsUsed;

    @Column(name = "item_shipped")
    private boolean itemShipped = false;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
