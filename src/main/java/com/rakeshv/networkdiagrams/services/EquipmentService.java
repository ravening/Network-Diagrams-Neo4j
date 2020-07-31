package com.rakeshv.networkdiagrams.services;

import com.rakeshv.networkdiagrams.models.Equipment;
import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.repositories.EquipmentRepository;
import com.rakeshv.networkdiagrams.repositories.InterfacesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Flux<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Mono<Equipment> getEquipmentByName(String equipmentName) {
        return equipmentRepository.findByName(equipmentName);
    }

    public Mono<Equipment> getEquipmentById(String id) {
        return equipmentRepository.findById(id);
    }

    public Mono<Equipment> saveEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Mono<Equipment> getEquipmentBySerial(String serial) {
        return equipmentRepository.findBySerialNumber(serial);
    }

    public Mono<Equipment> getEquipmentByEquipmentId(Long equipmentId) {
        return equipmentRepository.findByEquipmentId(equipmentId);
    }

    public Flux<Equipment> getAllEquipmentByBrand(String brand) {
        return equipmentRepository.findByBrandEqualsIgnoreCase(brand);
    }

    public Flux<Equipment> getAllEquipmentByOs(String os) {
        return equipmentRepository.findByOsEqualsIgnoreCase(os);
    }

    public Flux<Equipment> saveEquipments(List<Equipment> equipmentList) {
        return equipmentRepository.saveAll(equipmentList);
    }

    public Mono<Equipment> editEquipment(Equipment equipment) {
        Mono<Equipment> equipmentOptional = equipmentRepository.findById(equipment.getId());
        Equipment savedEquipment = equipmentOptional.block(Duration.ofSeconds(2));
        if (savedEquipment != null) {
            savedEquipment.setName(equipment.getName());
            savedEquipment.setEquipmentId(equipment.getEquipmentId());
            savedEquipment.setIpaddress(equipment.getIpaddress());
            savedEquipment.setModel(equipment.getModel());
            savedEquipment.setType(equipment.getType());
            savedEquipment.setInterfaces(equipment.getInterfaces());
            savedEquipment.setEquipmentSet(equipment.getEquipmentSet());
            return saveEquipment(savedEquipment);
        }
        return equipmentOptional;
    }

    public void deleteEquipment(String equipmentId) {
        Equipment equipmentMono = equipmentRepository.findById(equipmentId).block(Duration.ofSeconds(2));
        if (equipmentMono != null) {
            equipmentRepository.delete(equipmentMono).subscribe();
        }
    }

    public void deleteAllEquipment() {
        equipmentRepository.deleteAll().subscribe();
    }
}
