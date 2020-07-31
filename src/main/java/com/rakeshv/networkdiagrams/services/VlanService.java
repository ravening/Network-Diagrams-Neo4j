package com.rakeshv.networkdiagrams.services;

import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.repositories.VlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VlanService {
    private final VlanRepository vlanRepository;

    public Mono<Vlan> findVlanByVlanId(int vlanId) {
        return vlanRepository.findByVlanId(vlanId);
    }

    public Flux<Vlan> getAllVlan() {
        return vlanRepository.findAll();
    }

    public Mono<Vlan> findVlanById(Long id) {
        return vlanRepository.findById(id);
    }

    public Mono<Vlan> saveVlan(Vlan vlan) {
        return vlanRepository.save(vlan);
    }

    public Flux<Vlan> saveVlans(List<Vlan> vlanList) {
        return vlanRepository.saveAll(vlanList);
    }

    public Mono<Vlan> editVlan(Vlan vlan) {
        Vlan savedVlan = vlanRepository.findByVlanId(vlan.getVlanId()).block();
        if (savedVlan != null) {
            savedVlan.setLinks(vlan.getLinks());
            savedVlan.setVlanId(vlan.getVlanId());
            return vlanRepository.save(savedVlan);
        }
        return Mono.empty();
    }

    public boolean deleteVlan(int vlanId) {
        Vlan vlan = vlanRepository.findByVlanId(vlanId).block();
        if (vlan != null) {
            vlanRepository.delete(vlan);
            return true;
        }

        return  false;
    }

    public void deleteAll() {
        vlanRepository.deleteAll();
    }
}
