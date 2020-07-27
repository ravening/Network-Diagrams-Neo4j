package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Interfaces;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface InterfacesRepository extends ReactiveNeo4jRepository<Interfaces, Long> {
    Optional<Interfaces> findByName(String name);

    Optional<Interfaces> findByNameAndEquipment(String linkName, String equipmentName);
}
