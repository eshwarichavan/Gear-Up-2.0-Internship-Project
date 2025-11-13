package com.example.GearUp_GarageManagementSystem.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchandiseRequestDTO {

    @NotBlank(message = "Item name cannot be empty")
    private String itemName;

    @NotBlank(message = "Item description cannot be empty")
    private String itemDescription;

    @Min(value = 1, message = "Price in points must be at least 1")
    private Long pricePoints;

    private boolean inStock;
}
