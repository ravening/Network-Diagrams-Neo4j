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
public class InterfaceCsv {
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindByName(column = "macaddress")
    private String macAddress;
    @CsvBindByName(column = "ipaddress")
    private String ipAddress;
    @CsvBindByName(column = "equipment")
    private String equipment;
    @CsvBindByName(column = "description")
    private String description;
}
