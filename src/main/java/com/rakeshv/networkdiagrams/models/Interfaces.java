package com.rakeshv.networkdiagrams.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.springframework.data.core.schema.GeneratedValue;
import org.neo4j.springframework.data.core.schema.Id;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Interfaces {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String macAddress;
    private String ipAddress;
    private String equipment;
    private String description;
    private String neighborLink;
    private String neighborEquipment;
    private List<Integer> vlans;

    public void addVlan(int vlanId) {
        if (this.vlans == null) {
            this.vlans = new ArrayList<>();
        }

        vlans.add(vlanId);
    }
}
