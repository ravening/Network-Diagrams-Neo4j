package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Vlan;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface VlanRepository extends ReactiveNeo4jRepository<Vlan, Long> {
    Mono<Vlan> findByVlanId(int id);
}
