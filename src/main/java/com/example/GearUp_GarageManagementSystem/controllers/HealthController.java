package com.example.GearUp_GarageManagementSystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    //For Server Health Check :
    @GetMapping("/health")
    public String healthCheck(){
        return "ok";
    }
}
