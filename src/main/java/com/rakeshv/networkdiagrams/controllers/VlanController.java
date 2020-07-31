package com.rakeshv.networkdiagrams.controllers;

import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.services.VlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vlan")
public class VlanController {
    @Autowired
    VlanService vlanService;

    @GetMapping
    public Flux<Vlan> getAllVlans() {
        return vlanService.getAllVlan();
    }

    @GetMapping("/vlanid/{vlanid}")
    public Mono<Vlan> getVlanByVlanId(@PathVariable("vlanid") int id) {
        return vlanService.findVlanByVlanId(id);
    }

    @GetMapping("/{id}")
    public Mono<Vlan> getVlanById(@PathVariable("id") Long id) {
        return vlanService.findVlanById(id);
    }
}
