package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.entities.TypeDefect;
import com.Safra.defectrecord.services.FabricReportService;
import com.Safra.defectrecord.services.TypeDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/typeDefect")
public class TypeDefectController {



    @Autowired
    private TypeDefectService typeDefectService;

    @GetMapping("")
    public ResponseEntity<List<TypeDefect>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(typeDefectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeDefect> findById(@PathVariable String id){
        return  ResponseEntity.status(HttpStatus.OK).body(typeDefectService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<TypeDefect> create (@RequestBody TypeDefect typeDefect) {
        return ResponseEntity.status(HttpStatus.OK).body(typeDefectService.create(typeDefect));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TypeDefect> update(@PathVariable Long id, @RequestBody TypeDefect typeDefect){
        return ResponseEntity.status(HttpStatus.OK).body(typeDefectService.update(typeDefect));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  delete(@PathVariable String id){
        typeDefectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }


    @PostMapping("/saveAll")
    public ResponseEntity<List<TypeDefect>> create (@RequestBody List<TypeDefect> typeDefects) {
        return ResponseEntity.status(HttpStatus.OK).body(typeDefectService.createAll(typeDefects));
    }

}
