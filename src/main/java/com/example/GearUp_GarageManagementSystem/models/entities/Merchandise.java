package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Merchandise")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "itemName")
    private String itemName;

    @Column(name="item_Description",length = 1000)
    private String itemDescription;

    @Column(name = "price_Points")
    private Long pricePoints;

    @Column(name = "in_Stock")
    private boolean inStock = true;
}
