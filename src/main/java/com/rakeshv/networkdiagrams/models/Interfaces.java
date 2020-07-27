package com.rakeshv.networkdiagrams.models;

import lombok.Builder;
import lombok.Data;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;

@Builder
@Data
public class Interfaces {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String equipment;
    private String neighborLink;
    private String neighborEquipment;
    private String macAddress;
    private String description;
}
