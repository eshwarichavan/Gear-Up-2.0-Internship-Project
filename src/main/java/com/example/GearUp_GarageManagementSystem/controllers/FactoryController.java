package com.example.GearUp_GarageManagementSystem.controllers;

import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.FactoryResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.services.FactoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factories")
@Tag(name="Factory API",description = "This Factory Controller will manage CRUD operations")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
public class FactoryController {

    @Autowired
    private FactoryService factoryService;


    //create factory :
    @PostMapping("/create")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Owner can Create the factory")
    public FactoryResponseDTO createFactory(@RequestBody FactoryRequestDTO dto){
        return factoryService.createFactory(dto);
    }



    //update factory :
    @PutMapping("/update/{id}")
    @Operation(summary = "Owner can update the factory details")
    public FactoryResponseDTO updateFactory(
            @PathVariable Long id,
            @RequestBody FactoryRequestDTO dto
    ){
       return factoryService.updateFactory(id,dto);
    }


    //delete factory :
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('OWNER')")
    @Operation(summary = "Owner can delete the factory")
    public ResponseEntity<String> deleteFactory(@PathVariable Long id){
        factoryService.deleteFactory(id);
        return ResponseEntity.ok("Factory Deleted Successfully");
    }



    //get factories by id :
    @GetMapping("/getById/{id}")
    @Operation(summary = "Get the factory by id")
    public ResponseEntity<FactoryResponseDTO> getFactoryById(@PathVariable Long id){
        FactoryResponseDTO responseDTO=factoryService.getFactoryById(id);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/search")
    public ResponseEntity<?> searchFactories(@RequestParam String name) {
        List<Factory> factories = factoryService.searchFactoriesByName(name);
        if (factories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No factories found for name: " + name);
        }
        return ResponseEntity.ok(factories);
    }



    //get all factories :
    @GetMapping("/getAll")
    @Operation(summary = "Owner can get all the factory")
    public ResponseEntity<List<FactoryResponseDTO>> getAllFactories(){
        List<FactoryResponseDTO> responseDTOS=factoryService.getAllFactories();
        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/page")
    @Operation(summary = "Owner can get the factories by page")
    public ResponseEntity<Page<FactoryResponseDTO>> getFactoriesByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(factoryService.getFactoriesByPage(page, size));
    }
}
