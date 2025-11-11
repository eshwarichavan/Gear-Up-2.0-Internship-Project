package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "production")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productionCount;
    private Long targetCount;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;
}
