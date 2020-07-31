package com.rakeshv.networkdiagrams.controllers;

import com.rakeshv.networkdiagrams.models.Equipment;
import com.rakeshv.networkdiagrams.services.EquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Equipment Controller", tags = "All api for Equipments")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @ApiOperation(httpMethod = "GET", value = "Get all equipments in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping
    public Flux<Equipment> getEquipments() {
        return equipmentService.getAllEquipments();
    }

    @ApiOperation(httpMethod = "GET", value = "Get equipment by name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/name/{name}")
    public Mono<Equipment> getEquipmentByName(@PathVariable("name") String name) {
        return equipmentService.getEquipmentByName(name);
    }

    @ApiOperation(httpMethod = "GET", value = "Get equipment by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/{id}")
    public Mono<Equipment> getEquipmentById(@PathVariable("id") String id) {
        return equipmentService.getEquipmentById(id);
    }

    @ApiOperation(httpMethod = "GET", value = "Get equipment by serial number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/serial/{number}")
    public Mono<Equipment> getEquipmentBySerial(@PathVariable("number") String serial) {
        return equipmentService.getEquipmentBySerial(serial);
    }

    @ApiOperation(httpMethod = "GET", value = "Get equipment by equipment id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/equipmentid/{id}")
    public Mono<Equipment> getEquipmentByEquipmentId(@PathVariable("id") Long id) {
        return equipmentService.getEquipmentByEquipmentId(id);
    }

    @ApiOperation(httpMethod = "GET", value = "Get all equipments by brand name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/brand/{name}")
    public Flux<Equipment> getAllEquipmentByBrand(@PathVariable("name") String name) {
        return equipmentService.getAllEquipmentByBrand(name);
    }

    @ApiOperation(httpMethod = "GET", value = "Get equipment by os name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/os/{name}")
    public Flux<Equipment> getAllEquipmentByOS(@PathVariable("name") String os) {
        return equipmentService.getAllEquipmentByOs(os);
    }
}
