package com.example.GearUp_GarageManagementSystem.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name = "product_name" , nullable = false)
    private String productName;

    @Column(name = "productDescription", length = 2000)
    private String productDescription;

    @Column(name = "productPrice", length = 2000)
    private String productPrice;

    @Column(name = "product_image_url" , nullable = false)
    private String productImageUrl;

    @Column(name = "category" , nullable = false)
    private String category;

    @Column(name = "is_available" , nullable = false)
    private Boolean isAvailable=true;

    @Column(name = "quantity" , nullable = false)
    private Integer quantity;

    @CreationTimestamp
    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @Column(name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;



    //Mappings :
    @ManyToOne
    @JoinColumn(name = "factory_id")
    private Factory factory;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User owner;




}
