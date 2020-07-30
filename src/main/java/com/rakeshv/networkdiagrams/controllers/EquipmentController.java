package com.rakeshv.networkdiagrams.controllers;

import com.rakeshv.networkdiagrams.models.Equipment;
import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.services.EquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/equipments")
@Slf4j
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @GetMapping
    public Flux<Equipment> getEquipments() {
        return equipmentService.getAllEquipments();
    }

    @GetMapping("/name/{name}")
    public Mono<Equipment> getEquipmentByName(@PathVariable("name") String name) {
        return equipmentService.getEquipmentByName(name);
    }

    @GetMapping("/{id}")
    public Mono<Equipment> getEquipmentById(@PathVariable("id") String id) {
        return equipmentService.getEquipmentById(id);
    }
}
