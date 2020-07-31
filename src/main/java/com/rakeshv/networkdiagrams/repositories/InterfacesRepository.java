package com.rakeshv.networkdiagrams.repositories;

import com.rakeshv.networkdiagrams.models.Interfaces;
import org.neo4j.springframework.data.repository.ReactiveNeo4jRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface InterfacesRepository extends ReactiveNeo4jRepository<Interfaces, Long> {
    Mono<Interfaces> findByNameAndEquipment(String linkName, String equipmentName);

    Mono<Interfaces> findByMacAddress(String macAddress);

    Mono<Interfaces> findByIpAddress(String ipAddress);

    Flux<Interfaces> findByEquipmentEqualsIgnoreCase(String equipmentName);
}
