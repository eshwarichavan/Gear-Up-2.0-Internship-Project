package com.example.GearUp_GarageManagementSystem.models.entities;

import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="staff")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String userCity;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private String assignedFactory;

    @Column(nullable = false)
    private String assignedBay;

    private String profileImageURL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factory_id")
    private Factory factory;
}
