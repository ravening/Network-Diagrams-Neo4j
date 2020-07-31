package com.rakeshv.networkdiagrams.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Node;
import org.neo4j.springframework.data.core.schema.Property;
import org.neo4j.springframework.data.core.schema.Relationship;
import org.neo4j.springframework.data.core.support.UUIDStringGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node("Equipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Equipment {
    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

//    @Property("Equipment")
    private String name;
    private String ipaddress;
    private String model;
    private String type;
    private Long equipmentId;

    @Relationship(type = "CONNECTED_TO")
    private List<Equipment> equipmentSet;

    @Relationship(type = "PORT")
    private List<Interfaces> interfaces;

    public void addEquipment(Equipment equipment) {
        if (this.equipmentSet == null) {
            this.equipmentSet = new ArrayList<>();
        }
        this.equipmentSet.add(equipment);
    }

    public void addInterfaces(Interfaces interfaces) {
        if (this.interfaces == null) {
            this.interfaces = new ArrayList<>();
        }
        this.interfaces.add(interfaces);
    }
}
