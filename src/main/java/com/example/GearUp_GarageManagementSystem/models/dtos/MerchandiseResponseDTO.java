package com.example.GearUp_GarageManagementSystem.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseResponseDTO {

    private Long id;
    private String itemName;
    private String itemDescription;
    private Long pricePoints;
    private boolean inStock;
    private String message;
}
