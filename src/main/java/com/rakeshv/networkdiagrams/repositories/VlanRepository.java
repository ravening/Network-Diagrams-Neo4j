package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Vlan;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;

import java.util.Optional;

public interface VlanRepository extends ReactiveNeo4jRepository<Vlan, Long> {
    Optional<Vlan> findByVlanId(int id);
}
