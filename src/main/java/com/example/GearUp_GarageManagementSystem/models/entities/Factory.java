package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "factories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "factory_seq")
    @SequenceGenerator(name = "factory_seq", sequenceName = "factory_seq", allocationSize = 1)
    @Column(name = "factory_id")
    private Long factoryId;

    @Column(name = "factory_name", unique = true)
    private String factoryName;

    @Column(name = "city")
    private String city;

    @Column(name = "address", unique = true)
    private String address;


    //Mappings :

    //many factories can be owned by one owner :
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "central_office_id", nullable = true)
    private CentralOffice centralOffice;


    //many factories can be managed by one manager :
    @ManyToOne
    @JoinColumn(name = "plantHead_id", nullable = true)
    private User plantHead;

    //many factories can be owned by one owner :
    @ManyToOne
    @JoinColumn(name = "chiefSupervisor_id", nullable = true)
    private User chiefSupervisor;


    @ManyToMany
    @JoinTable(
            name = "factory_workers",
            joinColumns = @JoinColumn(name = "factory_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id")
    )
    private List<User> workers;


    @OneToMany(mappedBy = "factory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tool> tools = new ArrayList<>();

    @Column(name = "target_production_count")
    private Long targetProductionCount;


}
