package com.rakeshv.networkdiagrams.services;

import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.repositories.InterfacesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class InterfacesService {
    @Autowired
    InterfacesRepository interfacesRepository;

    public Flux<Interfaces> getInterfacesForEquipment(String equipmentName) {
        return interfacesRepository.findByEquipmentEqualsIgnoreCase(equipmentName);
    }

    public Mono<Interfaces> getInterfacesByNameAndEquipment(String name, String equipmentName) {
        return interfacesRepository.findByNameAndEquipment(name, equipmentName);
    }

    public Mono<Interfaces> getInterfacesByIpAddress(String ipAddress) {
        return interfacesRepository.findByIpAddress(ipAddress);
    }

    public Mono<Interfaces> getInterfacesByMacAddress(String macAddress) {
        return interfacesRepository.findByMacAddress(macAddress);
    }
}
