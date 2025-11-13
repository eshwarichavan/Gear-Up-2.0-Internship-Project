package com.example.GearUp_GarageManagementSystem.models.entities;

import com.example.GearUp_GarageManagementSystem.models.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "full_name")
    private String fullName;

    @Column(nullable = false, name = "email",unique = true)
    private String email;

    @Column(nullable = false, name = "password")
    private String password;


    @Column(updatable = false, name = "created_at", nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();

    private String address;
    private String profileImageURL;

    @Column(name = "assigned_factory",nullable = false,unique = true)
    private String assignedFactory;

    //Account status
    private boolean isActive=true;
    private boolean isVerified=true;


    // Injecting enum :
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="roles")
    private Roles roles;


    //Mappings :
    //owner will own many factories :
    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    private List<Factory> ownedFactories;


    //one manager can manage many factories :
    @OneToMany(mappedBy = "plantHead",cascade = CascadeType.ALL)
    private List<Factory> managedFactories;


    //chief can supervise multiple factories :
    @OneToMany(mappedBy = "chiefSupervisor")
    private List<Factory> supervisedFactories;


    //worker can work in multiple factories
    @ManyToMany(mappedBy = "workers")
    private List<Factory> workingFactories;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "central_office_id")
    private CentralOffice centralOffice;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Rewards rewards;



}
