package com.rakeshv.networkdiagrams.utils;

import com.rakeshv.networkdiagrams.models.Equipment;
import com.rakeshv.networkdiagrams.models.Interfaces;
import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.repositories.EquipmentRepository;
import com.rakeshv.networkdiagrams.repositories.InterfacesRepository;
import com.rakeshv.networkdiagrams.repositories.VlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DbSeeder {
    private final EquipmentRepository equipmentRepository;
    private final InterfacesRepository interfacesRepository;
    private final VlanRepository vlanRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void saveEntries() {
        equipmentRepository.deleteAll().block();
        interfacesRepository.deleteAll().block();
        vlanRepository.deleteAll().block();
        Equipment arista = Equipment.builder()
                .name("arista")
                .ipaddress("1.1.1.1")
                .model("switch")
                .type("switch")
                .equipmentId(1234L).build();

        Equipment juniper = Equipment.builder()
                .name("Juniper")
                .ipaddress("2.2.2.2")
                .model("mx")
                .type("router")
                .equipmentId(4567L)
                .equipmentSet(new HashSet<>())
                .interfaces(new HashSet<>()).build();

        Equipment node01 = Equipment.builder()
                .name("node01")
                .ipaddress("10.10.10.10")
                .model("dell")
                .type("hypervisor")
                .equipmentId(1111L)
                .equipmentSet(new HashSet<>())
                .interfaces(new HashSet<>()).build();

        Equipment node99 = Equipment.builder()
                .name("node99")
                .ipaddress("99.99.99.99")
                .model("hp")
                .type("hypervisor")
                .equipmentId(2222L)
                .equipmentSet(new HashSet<>())
                .interfaces(new HashSet<>()).build();

        Interfaces aristaEth0 = Interfaces.builder()
                .name("eth0")
                .equipment("arista")
                .neighborLink("eth2")
                .neighborEquipment("juniper").build();
        Interfaces juniperEth1 = Interfaces.builder()
                .name("eth1")
                .equipment("juniper")
                .neighborLink("eth99")
                .neighborEquipment("node99").build();
        Interfaces juniperEth2 = Interfaces.builder()
                .name("eth2")
                .equipment("juniper")
                .neighborLink("eth0")
                .neighborEquipment("arista").build();

        Interfaces node01Eth40 = Interfaces.builder()
                .name("eth40")
                .equipment("node01")
                .neighborLink("eth50")
                .neighborEquipment("arista").build();

        Interfaces aristaEth50 = Interfaces.builder()
                .name("eth50")
                .equipment("arista")
                .neighborLink("eth40")
                .neighborEquipment("node01").build();

        Interfaces node99Interface = Interfaces.builder()
                .name("eth99")
                .equipment("node99")
                .neighborLink("eth01")
                .neighborEquipment("juniper").build();

        IntStream stream = IntStream.rangeClosed(1, 10);
        List<Vlan> vlanList = stream
                .mapToObj(n -> Vlan.builder().vlanId(n).build())
                .collect(Collectors.toList());
        IntStream stream1 = IntStream.rangeClosed(11, 20);
        List<Vlan> vlans = stream1
                .mapToObj(n -> Vlan.builder().vlanId(n).build())
                .collect(Collectors.toList());

        vlanList.get(9).addInterface(node01Eth40);
        vlanList.get(9).addInterface(aristaEth0);
        vlanList.get(9).addInterface(aristaEth50);
        vlanList.get(9).addInterface(juniperEth2);
        vlanList.get(9).addInterface(juniperEth1);
        vlanList.get(9).addInterface(node99Interface);
        vlanList.get(4).addInterface(node01Eth40);
        vlanList.get(0).addInterface(aristaEth0);
        vlanList.get(0).addInterface(aristaEth50);
        vlanList.get(0).addInterface(juniperEth1);
        vlanList.get(1).addInterface(juniperEth2);
        vlanList.get(2).addInterface(node99Interface);
        vlanList.get(3).addInterface(node01Eth40);
        vlanList.get(4).addInterface(node01Eth40);
        vlanList.get(5).addInterface(juniperEth2);
        vlanList.forEach(vlan -> vlanRepository.save(vlan).subscribe());

        vlans.get(9).addInterface(node01Eth40);
        vlans.get(9).addInterface(aristaEth0);
        vlans.get(9).addInterface(aristaEth50);
        vlans.get(9).addInterface(juniperEth2);
        vlans.get(9).addInterface(juniperEth1);
        vlans.get(9).addInterface(node99Interface);
        vlans.get(4).addInterface(node01Eth40);
        vlans.get(0).addInterface(aristaEth0);
        vlans.get(0).addInterface(aristaEth50);
        vlans.get(0).addInterface(juniperEth1);
        vlans.get(1).addInterface(juniperEth2);
        vlans.get(2).addInterface(node99Interface);
        vlans.get(3).addInterface(node01Eth40);
        vlans.get(4).addInterface(node01Eth40);
        vlans.get(5).addInterface(juniperEth2);
        vlans.forEach(vlan -> vlanRepository.save(vlan).subscribe());

        List<Equipment> equipmentList = List.of(arista, node01, juniper, node99);
        equipmentList.forEach(equipment -> equipmentRepository.save(equipment).block(Duration.ofSeconds(2)));
        arista = equipmentRepository.findByName("arista").block(Duration.ofSeconds(2));
        node01 = equipmentRepository.findByName("node01").block(Duration.ofSeconds(2));
        juniper = equipmentRepository.findByName("Juniper").block(Duration.ofSeconds(2));
        node99 = equipmentRepository.findByName("node99").block(Duration.ofSeconds(2));

        if (arista != null) {
            arista.addInterfaces(aristaEth50);
            arista.addInterfaces(aristaEth0);
            equipmentRepository.save(arista).block();
        }
        if (node01 != null) {
            node01.addInterfaces(node01Eth40);
            equipmentRepository.save(node01).block();
        }
        if (juniper != null) {
            juniper.addInterfaces(juniperEth1);
            juniper.addInterfaces(juniperEth2);
            equipmentRepository.save(juniper).block();
        }
        if (node99 != null) {
            node99.addInterfaces(node99Interface);
            equipmentRepository.save(node99).block();
        }

        arista = equipmentRepository.findByName("arista").block(Duration.ofSeconds(2));
        node01 = equipmentRepository.findByName("node01").block(Duration.ofSeconds(2));
        juniper = equipmentRepository.findByName("Juniper").block(Duration.ofSeconds(2));
        node99 = equipmentRepository.findByName("node99").block(Duration.ofSeconds(2));
        if (node01 != null && arista != null) {
            node01.addEquipment(arista);
            equipmentRepository.save(node01).block();
        }
        if (arista != null && juniper != null) {
            arista.addEquipment(juniper);
            equipmentRepository.save(arista).block();
        }
        if (node99 != null && juniper != null) {
            juniper.addEquipment(node99);
            equipmentRepository.save(juniper).block();
        }
    }
}
