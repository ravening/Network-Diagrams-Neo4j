package com.rakeshv.networkdiagrams.services;

import com.rakeshv.networkdiagrams.models.ConnectionsCsv;
import com.rakeshv.networkdiagrams.models.Equipment;
import com.rakeshv.networkdiagrams.models.EquipmentCsv;
import com.rakeshv.networkdiagrams.models.ExecutionStatus;
import com.rakeshv.networkdiagrams.models.InterfaceCsv;
import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.models.VlanCsv;
import com.rakeshv.networkdiagrams.repositories.EquipmentRepository;
import com.rakeshv.networkdiagrams.repositories.InterfacesRepository;
import com.rakeshv.networkdiagrams.repositories.VlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UploadService {
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    VlanRepository vlanRepository;
    @Autowired
    InterfacesRepository interfacesRepository;

    public ExecutionStatus saveEquipments(List<EquipmentCsv> equipmentList) {
        List<Equipment> equipmentArrayList = new ArrayList<>();
        for (EquipmentCsv equipmentCsv : equipmentList) {
            Equipment equipment = equipmentRepository.findByName(equipmentCsv.getName()).block(Duration.ofSeconds(2));
            if (equipment == null) {
                equipment = Equipment.builder().build();
            }
            equipment.setName(equipmentCsv.getName());
            equipment.setIpaddress(equipmentCsv.getIpAddress());
            equipment.setBrand(equipmentCsv.getBrand());
            equipment.setModel(equipmentCsv.getModel());
            equipment.setType(equipmentCsv.getType());
            equipment.setOs(equipmentCsv.getOs());
            equipment.setSerialNumber(equipmentCsv.getSerialNumber());
            equipment.setEquipmentId(Long.parseLong(equipmentCsv.getEquipmentId()));
            equipment.setRackName(equipmentCsv.getRackName());
            equipment.setZone(equipmentCsv.getZone());
            equipmentArrayList.add(equipment);
        }
        log.info("saving equipments");
        equipmentRepository.saveAll(equipmentArrayList).subscribe();
        return ExecutionStatus.builder()
                .message("")
                .status(true)
                .httpStatus(HttpStatus.OK).build();
    }

    public ExecutionStatus saveInterface(List<InterfaceCsv> interfaceList) {
        Map<String, Equipment> equipmentMap = new HashMap<>();
        for (InterfaceCsv interfaceCsv : interfaceList) {
            Equipment equipment;
            if (equipmentMap.containsKey(interfaceCsv.getEquipment())) {
                equipment = equipmentMap.get(interfaceCsv.getEquipment());
            } else {
                equipment = equipmentRepository.findByName(interfaceCsv.getEquipment()).block(Duration.ofSeconds(2));
                if (equipment == null) {
                    equipment = Equipment.builder().build();
                }
            }
            Interfaces interfaces = Interfaces.builder()
                    .name(interfaceCsv.getName())
                    .macAddress(interfaceCsv.getMacAddress())
                    .ipAddress(interfaceCsv.getIpAddress())
                    .equipment(interfaceCsv.getEquipment()).build();
            equipment.addInterfaces(interfaces);
            equipmentMap.put(interfaceCsv.getEquipment(), equipment);
        }

        for (Equipment equipment : equipmentMap.values()) {
            log.info("interfaces for equipment {} are {}", equipment.getName(), equipment.getInterfaces());
            equipmentRepository.save(equipment).subscribe();
        }
        log.info("saving interfaces");

        return ExecutionStatus.builder()
                .message("")
                .status(true)
                .httpStatus(HttpStatus.OK).build();
    }

    public ExecutionStatus saveConnections(List<ConnectionsCsv> connectionsList) {
        boolean success = true;
        StringBuilder stringBuilder = new StringBuilder();
        for (ConnectionsCsv connection : connectionsList) {
            Equipment sourceNode = equipmentRepository.findByName(connection.getSourceNode()).block(Duration.ofSeconds(3));
            Equipment targetNode = equipmentRepository.findByName(connection.getTargetNode()).block(Duration.ofSeconds(3));

            if (sourceNode == null || targetNode == null) {
                log.error("Unable to find either source or target node. continuing");
                stringBuilder.append("Unable to find either source of target node ")
                        .append(sourceNode).append(",").append(targetNode).append("\n");
                success = false;
                continue;
            }

            for (Interfaces interfaces : sourceNode.getInterfaces()) {
                if (interfaces.getName().equalsIgnoreCase(connection.getSourcePort())) {
                    interfaces.setNeighborLink(connection.getTargetPort());
                    interfaces.setNeighborEquipment(connection.getTargetNode());
                    log.info("port {}: neighbour port : {}, neighbor device: {}",
                            interfaces.getName(), interfaces.getNeighborLink(), interfaces.getNeighborEquipment());
                }
            }
            for (Interfaces interfaces : targetNode.getInterfaces()) {
                if (interfaces.getName().equalsIgnoreCase(connection.getTargetPort())) {
                    interfaces.setNeighborLink(connection.getSourcePort());
                    interfaces.setNeighborEquipment(connection.getSourceNode());
                    log.info("port {}: neighbour port : {}, neighbor device: {}",
                            interfaces.getName(), interfaces.getNeighborLink(), interfaces.getNeighborEquipment());
                }
            }

            log.info("source node {}, target node {}", sourceNode.getName(), targetNode.getName());
            sourceNode.addEquipment(targetNode);

            // create connections
            log.info("Creating connection between {}-{} and {}-{}",
                    sourceNode.getName(), connection.getSourcePort(), targetNode.getName(), connection.getTargetPort());

            equipmentRepository.save(sourceNode).block();
            equipmentRepository.save(targetNode).block();
        }

        return ExecutionStatus.builder()
                .message(stringBuilder.toString())
                .status(success)
                .httpStatus(success ? HttpStatus.OK : HttpStatus.NOT_FOUND).build();
    }

    public ExecutionStatus saveVlans(List<VlanCsv> vlanCsvList) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Vlan> vlanMap = new HashMap<>();
        Map<String, Interfaces> interfacesMap = new HashMap<>();
        for (VlanCsv vlanCsv : vlanCsvList) {
            String[] interfaceList = vlanCsv.getInterfaces().split(",");
            String[] vlans = vlanCsv.getVlanId().split(",");
            for (String interfaceName : interfaceList) {
                Interfaces interfaces;
                Vlan vlan;
                if (interfacesMap.containsKey(vlanCsv.getEquipment() + interfaceName)) {
                    interfaces = interfacesMap.get(vlanCsv.getEquipment() + interfaceName);
                } else {
                    interfaces = interfacesRepository
                            .findByNameAndEquipment(interfaceName, vlanCsv.getEquipment()).block();
                    if (interfaces == null) {
                        interfaces = Interfaces.builder().build();
                    }
                }
                for (String v : vlans) {
                    interfaces.addVlan(Integer.parseInt(v));
                    if (vlanMap.containsKey(v)) {
                        vlan = vlanMap.get(v);
                    } else {
                        vlan = vlanRepository.findByVlanId(Integer.parseInt(v)).block();
                        if (vlan == null) {
                            vlan = Vlan.builder().build();
                        }

                    }
                    vlan.setVlanId(Integer.parseInt(v));
                    vlan.addInterface(interfaces);
                    vlanMap.put(v, vlan);
                    interfacesMap.put(vlanCsv.getEquipment() + interfaceName, interfaces);
                }
            }
        }

        for (Interfaces interfaces : interfacesMap.values()) {
            log.info("saving interface {}" , interfaces);
            interfacesRepository.save(interfaces).block();
        }
        for (Vlan vlan : vlanMap.values()) {
            log.info("saving vlan {}", vlan);
            vlanRepository.save(vlan).block();
        }

        return ExecutionStatus.builder()
                .message(stringBuilder.toString())
                .status(true)
                .httpStatus(HttpStatus.OK).build();
    }
}
