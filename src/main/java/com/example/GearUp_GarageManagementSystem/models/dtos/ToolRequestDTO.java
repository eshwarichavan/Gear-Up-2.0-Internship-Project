    package com.example.GearUp_GarageManagementSystem.models.dtos;

    import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Positive;
    import jakarta.validation.constraints.Size;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ToolRequestDTO {


        private Long id;

        @NotBlank(message = "Tool Name cannot be empty")
        private String toolName;

        @NotBlank(message = "Tool Type cannot be empty")
        private String toolType;

        @NotBlank(message = "Tool Category cannot be empty")
        private String toolCategory;

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Tool Quantity must be a positive number")
        private Long quantity;

        @NotNull(message = "Tool Nature cannot be null")
        private ToolNature toolNature;

        @NotNull(message = "Factory Id cannot be null")
        @Positive(message = "Factory Id must be a positive number")
        private Long factoryId;
    }
