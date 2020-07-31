package com.rakeshv.networkdiagrams.controllers;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rakeshv.networkdiagrams.models.ConnectionsCsv;
import com.rakeshv.networkdiagrams.models.EquipmentCsv;
import com.rakeshv.networkdiagrams.models.ExecutionStatus;
import com.rakeshv.networkdiagrams.models.InterfaceCsv;
import com.rakeshv.networkdiagrams.models.VlanCsv;
import com.rakeshv.networkdiagrams.services.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @Autowired
    UploadService uploadService;

    @GetMapping
    public String upload() {
        return "upload";
    }

    @GetMapping("/equipment")
    public String equipment() {
        return "equipment";
    }

    @GetMapping("/interfaces")
    public String interfaces() {
        return "interfaces";
    }

    @GetMapping("/connections")
    public String connections() {
        return "connections";
    }

    @GetMapping("/vlan")
    public String vlan() {
        return "vlan";
    }

    @PostMapping("/equipment/upload-csv-file")
    public String uploadCsvFile(@RequestParam("file") MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<EquipmentCsv> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(EquipmentCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withKeepCarriageReturn(false)
                        .build();

                // convert `CsvToBean` object to list of users
                List<EquipmentCsv> equipments = csvToBean.parse();

                uploadService.saveEquipments(equipments);
                log.info("Equipments successfully saved");
                // save users list on model
                model.addAttribute("equipments", equipments);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file. " + ex.getMessage());
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    @PostMapping("/interfaces/upload-interface-file")
    public String uploadInterfaces(@RequestParam("file") MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<InterfaceCsv> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(InterfaceCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreQuotations(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<InterfaceCsv> interfaceCsvList = csvToBean.parse();
                ExecutionStatus status = uploadService.saveInterface(interfaceCsvList);
                // save users list on model
                model.addAttribute("message", status.getMessage());
                model.addAttribute("status", status.isStatus());

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file. " + ex.getLocalizedMessage());
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    @PostMapping("/connections/upload-connections-file")
    public String uploadConnections(@RequestParam("file") MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<ConnectionsCsv> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(ConnectionsCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<ConnectionsCsv> connectionsCsvList = csvToBean.parse();
                ExecutionStatus status = uploadService.saveConnections(connectionsCsvList);
                // save users list on model
                model.addAttribute("message", status.getMessage());
                model.addAttribute("status", status.isStatus());

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file. " + ex.getMessage());
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }

    @PostMapping("/vlans/upload-vlan-file")
    public String uploadVlans(@RequestParam("file") MultipartFile file, Model model) {
        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<VlanCsv> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(VlanCsv.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withSeparator(':')
                        .build();

                // convert `CsvToBean` object to list of users
                List<VlanCsv> vlanCsvList = csvToBean.parse();
                vlanCsvList.forEach(vlanCsv -> log.info("vlan id {}, interfaces are {}, equipments are {}",
                        vlanCsv.getVlanId(), vlanCsv.getInterfaces(), vlanCsv.getEquipment()));
                ExecutionStatus status = uploadService.saveVlans(vlanCsvList);
                // save users list on model
                model.addAttribute("message", status.getMessage());
                model.addAttribute("status", status.isStatus());

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file. " + ex.getMessage());
                model.addAttribute("status", false);
            }
        }
        return "file-upload-status";
    }
}
