package com.example.GearUp_GarageManagementSystem.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedemptionRequestDTO {

    @NotNull(message = "Distributor ID is required")
    private String distributorEmail;

    @NotNull(message = "Merchandise ID is required")
    private String  merchandiseName;

    @NotNull(message = "Points used are required")
    @Min(value = 1, message = "Points used must be greater than zero")
    private Long pointsUsed;

    @Pattern(regexp = "PENDING|SHIPPED|CANCELLED",
            message = "Status must be PENDING, SHIPPED, or CANCELLED")
    private String status;
}
