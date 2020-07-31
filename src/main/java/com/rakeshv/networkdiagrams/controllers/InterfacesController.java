package com.rakeshv.networkdiagrams.controllers;

import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.services.InterfacesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/interfaces")
@Api(value = "Interfaces Controller", tags = "All api for Interfaces")
public class InterfacesController {
    @Autowired
    InterfacesService interfacesService;

    @ApiOperation(httpMethod = "GET", value = "Get interface by IP address")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Interface Not Found")
    })
    @GetMapping("/ipaddress/{ipaddress}")
    public Mono<Interfaces> getInterfacesByIpAddress(@PathVariable("ipaddress") String ipaddress) {
        return interfacesService.getInterfacesByIpAddress(ipaddress);
    }

    @ApiOperation(httpMethod = "GET", value = "Get interface by MAC address")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Interface Not Found")
    })
    @GetMapping("/macaddress/{macaddress}")
    public Mono<Interfaces> getInterfacesByMacAddress(@PathVariable("macaddress") String macaddress) {
        return interfacesService.getInterfacesByMacAddress(macaddress);
    }

    @ApiOperation(httpMethod = "GET", value = "Get interface by interface name and equipment name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Interface Not Found")
    })
    @GetMapping("/name/{name}/equipmentname/{equipmentname}")
    public Mono<Interfaces> getInterfacesByNameAndEquipmentName(@PathVariable("name") String name,
                                                                @PathVariable("equipmentname") String equipmentname) {
        return interfacesService.getInterfacesByNameAndEquipment(name, equipmentname);
    }

    @ApiOperation(httpMethod = "GET", value = "Get all interfaces belonging to an equipment")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping("/equipmentname/{equipmentname}")
    public Flux<Interfaces> getInterfacesByEquipmentName(@PathVariable("equipmentname") String equipmentname) {
        return interfacesService.getInterfacesForEquipment(equipmentname);
    }
}
