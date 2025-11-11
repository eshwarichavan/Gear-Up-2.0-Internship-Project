package com.example.GearUp_GarageManagementSystem.models.dtos;

import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolResponseDTO {

    private Long id;
    private String message; //tool added successfully.
    private String toolName;
    private String toolType;
    private String toolCategory;
    private Long quantity;
    private ToolNature toolNature;
    private String factoryName;
}
