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
    @CsvBindByName(column = "brand")
    private String brand;
    @CsvBindByName(column = "model")
    private String model;
    @CsvBindByName(column = "type")
    private String type;
    @CsvBindByName(column = "os")
    private String os;
    @CsvBindByName(column = "serialnumber")
    private String serialNumber;
    @CsvBindByName(column = "equipmentid")
    private String equipmentId;
    @CsvBindByName(column = "rackname")
    private String rackName;
    @CsvBindByName(column = "zone")
    private String zone;
}
