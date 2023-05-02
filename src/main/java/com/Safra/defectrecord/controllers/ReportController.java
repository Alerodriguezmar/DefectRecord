package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {


    @Autowired
    private ReportService reportService;

    @GetMapping("/{supplier}")
    public ResponseEntity<FileSystemResource> findAllFabricSupplier(@PathVariable String supplier) throws IOException {
        reportService.generatedReportByFabricSupplier(supplier);

//
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                .filename("Filename.docx")
                .build();

        //Crear un encabezado HTTP para el archivo
        HttpHeaders encabezados = new HttpHeaders();
        encabezados.setContentDisposition(contentDisposition);

        File doc = new File("output.docx");
        FileSystemResource fileSystemResource = new FileSystemResource(doc);


        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(encabezados)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(fileSystemResource);
    }

}
