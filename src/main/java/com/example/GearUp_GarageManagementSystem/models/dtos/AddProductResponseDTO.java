package com.example.GearUp_GarageManagementSystem.models.dtos;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductResponseDTO {

    private Long id;
    private String productName;
    private String category;
    private String productPrice;
    private Integer quantity;
    private String factoryName;
    private Boolean isAvailable;
    private String productImageURL;
}
