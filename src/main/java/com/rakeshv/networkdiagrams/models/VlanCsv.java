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
public class VlanCsv {
    @CsvBindByName(column = "equipment")
    private String equipment;
    @CsvBindByName(column = "interfaces")
    private String interfaces;
    @CsvBindByName(column = "vlanid")
    private String vlanId;
}
