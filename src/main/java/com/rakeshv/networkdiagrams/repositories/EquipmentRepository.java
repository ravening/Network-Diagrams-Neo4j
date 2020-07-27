package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Equipment;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Mono;

public interface EquipmentRepository extends ReactiveNeo4jRepository<Equipment, String> {
    Mono<Equipment> findByName(String name);
}
