package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "central_offices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CentralOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    private Long officeId;

    @Column(name = "office_name")
    private String centralOfficeName;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name="created_at")
    private LocalDateTime createdAt;


    //Mappings :
    @OneToMany(mappedBy = "centralOffice",cascade = CascadeType.ALL,fetch =FetchType.LAZY )
    private List<Factory> factories;


    // One central office can have multiple officers
    @OneToMany(mappedBy = "centralOffice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> centralOfficers;


}
