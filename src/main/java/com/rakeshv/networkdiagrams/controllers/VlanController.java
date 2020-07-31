package com.rakeshv.networkdiagrams.controllers;

import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.services.VlanService;
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
@RequestMapping("/api/vlan")
@Api(value = "Vlan Controller", tags = "All api for Vlans")
public class VlanController {
    @Autowired
    VlanService vlanService;

    @ApiOperation(httpMethod = "GET", value = "Get all vlans in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Equipment Not Found")
    })
    @GetMapping
    public Flux<Vlan> getAllVlans() {
        return vlanService.getAllVlan();
    }

    @ApiOperation(httpMethod = "GET", value = "Get vlan by vlanid")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Vlan id Not Found")
    })
    @GetMapping("/vlanid/{vlanid}")
    public Mono<Vlan> getVlanByVlanId(@PathVariable("vlanid") int id) {
        return vlanService.findVlanByVlanId(id);
    }

    @ApiOperation(httpMethod = "GET", value = "Get vlan by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK !"),
            @ApiResponse(code = 500, message = "Internal Error"),
            @ApiResponse(code = 404, message = "Vlan with id Not Found")
    })
    @GetMapping("/{id}")
    public Mono<Vlan> getVlanById(@PathVariable("id") Long id) {
        return vlanService.findVlanById(id);
    }
}
