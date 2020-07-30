package com.rakeshv.networkdiagrams.models;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipmentCsv {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "ipaddress")
    private String ipAddress;
    @CsvBindByName(column = "model")
    private String model;
    @CsvBindByName(column = "version")
    private String version;
    @CsvBindByName(column = "type")
    private String type;
    @CsvBindByName(column = "equipmentid")
    private String equipmentId;
}
