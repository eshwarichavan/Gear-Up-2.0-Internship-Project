package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.AddProductRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.AddProductResponseDTO;
import com.example.GearUp_GarageManagementSystem.repositories.ProductRepository;
import com.example.GearUp_GarageManagementSystem.services.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Tag(name="Product API")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@Validated
public class ProductController {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Owner can add Products")
    public ResponseEntity<String> addProduct(@Valid @RequestBody AddProductRequestDTO dto) {
        productService.addProduct(dto);  // no user info required
        return ResponseEntity.ok("Product added successfully!");
    }


    @GetMapping("/all")
    @Operation(summary = "Owner can get all products")
    public ResponseEntity<List<AddProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }



    @GetMapping("/{id}")
    @Operation(summary = "Owner can add Products by Id")
    public ResponseEntity<AddProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }



    @PutMapping("/update/{id}")
    @Operation(summary = "Owner can update Products")
    public ResponseEntity<AddProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestBody AddProductRequestDTO dto
    ) {
        return ResponseEntity.ok(productService.updateProductDetails(id, dto));
    }




    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Owner can add Products")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }



    @GetMapping("/filter")
    @Operation(summary = "Owner can filter the Products")
    public ResponseEntity<List<AddProductResponseDTO>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) Long factoryId
    ) {
        return ResponseEntity.ok(productService.filterProducts(category, isAvailable, factoryId));
    }




    @GetMapping("/paged")
    public ResponseEntity<Page<AddProductResponseDTO>> getProductsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "productName") String sortBy,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isAvailable,
            @RequestParam(required = false) Long factoryId
    ) {
        Page<AddProductResponseDTO> products = productService.getProductsPagedFiltered(
                category, isAvailable, factoryId, page, size, sortBy);
        return ResponseEntity.ok(products);
    }

}
