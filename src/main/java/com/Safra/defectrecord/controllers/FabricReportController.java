package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.services.FabricReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/fabricReport")
public class FabricReportController {


    @Autowired
    private FabricReportService fabricReportService;

    @GetMapping("")
    public ResponseEntity<List<FabricReport>> findAllFabricSupplier() {
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricReport> findById(@PathVariable String id){
        return  ResponseEntity.status(HttpStatus.OK).body(fabricReportService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<FabricReport> create (@RequestBody FabricReport fabricReport) {
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.create(fabricReport));
    }


    @PostMapping("addFile")
    public ResponseEntity<FabricReport> createDFile (@RequestPart ("report") FabricReport fabricReport,@RequestPart ("file") MultipartFile[] file) throws IOException {

        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.createFile(fabricReport,file));
    }


    @PutMapping("/{id}")
    public ResponseEntity<FabricReport> update(@PathVariable Long id, @RequestBody FabricReport fabricReport){
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.update(fabricReport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  delete(@PathVariable String id){
        fabricReportService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }


    @PostMapping("/saveAll")
    public ResponseEntity<List<FabricReport>> create (@RequestBody List<FabricReport> fabricReports) {
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.createAll(fabricReports));
    }


    @GetMapping("/findByDate/{startDate}/{endDate}")
    public ResponseEntity<List<FabricReport>> findByDate (@PathVariable String startDate , @PathVariable String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateNow = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateNow = LocalDate.parse(endDate, formatter).atStartOfDay();
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.findByCreationDateBetween(startDateNow,endDateNow));
    }


    @GetMapping("/findByDateAndSupplier/{startDate}/{endDate}/{supplier}")
    public ResponseEntity<List<FabricReport>> findByDateAndSupplier (@PathVariable String startDate , @PathVariable String endDate, @PathVariable String  supplier) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateNow = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateNow = LocalDate.parse(endDate, formatter).atStartOfDay();
        return ResponseEntity.status(HttpStatus.OK).body(fabricReportService.findByCreationDateBetweenAndFabricSupplierSupplier(startDateNow,endDateNow,supplier));
    }


}
