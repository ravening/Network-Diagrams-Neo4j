package com.rakeshv.networkdiagrams.services;

import com.rakeshv.networkdiagrams.models.Vlan;
import com.rakeshv.networkdiagrams.repositories.VlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VlanService {
    private final VlanRepository vlanRepository;

    public Optional<Vlan> findVlanByVlanId(int vlanId) {
        return vlanRepository.findByVlanId(vlanId);
    }

    public Flux<Vlan> getAllVlan() {
        return vlanRepository.findAll();
    }

    public Mono<Vlan> saveVlan(Vlan vlan) {
        return vlanRepository.save(vlan);
    }

    public Flux<Vlan> saveVlans(List<Vlan> vlanList) {
        return vlanRepository.saveAll(vlanList);
    }

    public Mono<Vlan> editVlan(Vlan vlan) {
        Optional<Vlan> vlanOptional = vlanRepository.findByVlanId(vlan.getVlanId());
        if (vlanOptional.isPresent()) {
            Vlan savedVlan = vlanOptional.get();
            savedVlan.setLinks(vlan.getLinks());
            savedVlan.setVlanId(vlan.getVlanId());
            return vlanRepository.save(savedVlan);
        }
        return Mono.empty();
    }

    public boolean deleteVlan(int vlanId) {
        Optional<Vlan> vlanOptional = vlanRepository.findByVlanId(vlanId);
        if (vlanOptional.isPresent()) {
            vlanRepository.delete(vlanOptional.get());
            return true;
        }

        return  false;
    }

    public void deleteAll() {
        vlanRepository.deleteAll();
    }
}
