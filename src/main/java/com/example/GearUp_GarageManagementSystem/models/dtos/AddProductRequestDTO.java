package com.example.GearUp_GarageManagementSystem.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequestDTO {

    private Long id ;

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    private String productName;

    @NotBlank(message = "Product description is required")
    @Size(max = 2000, message = "Product description cannot exceed 2000 characters")
    private String productDescription;

    @NotNull(message = "Product price is required")
    @Min(value = 1, message = "Product price must be at least 1")
    private String productPrice;

    @NotBlank(message = "Product image URL is required")
    private String productImageURL;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Availability must be specified")
    private Boolean isAvailable;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Factory ID is required")
    private Long factoryId;

}
