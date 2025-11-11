package com.example.GearUp_GarageManagementSystem.models.entities;

import com.example.GearUp_GarageManagementSystem.models.enums.ToolCategory;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolNature;
import com.example.GearUp_GarageManagementSystem.models.enums.ToolUsage;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tools")
@Builder
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="tool_id")
    private Long toolId;

    @Column(name="tool_type")
    private String toolType;    // e.g., wrench, screwdriver, diagnostic device, etc.

    @Column(name="tool_name")
    private String toolName;  //small screwdriver

    @Column(name="quantity")
    private Long quantity;


    // Enums :
    @Enumerated(EnumType.STRING)
    @Column(name = "tool_Nature",nullable = false)
    private ToolNature toolNature;

    @Enumerated(EnumType.STRING)
    @Column(name="tool_category",nullable = false)
    private ToolCategory toolCategory;   //expensive / regular


    @Enumerated(EnumType.STRING)
    @Column(name="tool_usage",nullable = false)
    private ToolUsage toolUsage;  // FREQUENTLY_USED / RARELY_USED


    //Mappings :
    //many tool belong to one factory only :
    @ManyToOne
    @JoinColumn(name="factory_id")
    private Factory factory;
}
