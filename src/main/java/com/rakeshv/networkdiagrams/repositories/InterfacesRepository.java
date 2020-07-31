package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Interfaces;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface InterfacesRepository extends ReactiveNeo4jRepository<Interfaces, Long> {
    Mono<Interfaces> findByNameAndEquipment(String linkName, String equipmentName);
}
