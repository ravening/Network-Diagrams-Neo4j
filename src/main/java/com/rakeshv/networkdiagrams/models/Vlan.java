package com.rakeshv.networkdiagrams.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;
import org.neo4j.springframework.data.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vlan {
    @Id
    @GeneratedValue
    private Long id;
    private int vlanId;

    @Relationship(type = "VLAN")
    private Set<Interfaces> links;

    public void addInterface(Interfaces interfaces) {
        if (this.links == null) {
            this.links = new HashSet<>();
        }
        this.links.add(interfaces);
    }
}
