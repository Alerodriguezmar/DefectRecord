package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.entities.OIBT;
import com.Safra.defectrecord.services.OIBTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("OIBT")

public class OIBTController {



    @Autowired
    private OIBTService oibtService;


    @GetMapping()
    public ResponseEntity<List<OIBT>> findAllByBatchNum(@RequestParam(name = "batchNum" ) String batchNum){
        return ResponseEntity.status(HttpStatus.OK).body(oibtService.findAllByBatchNum(batchNum));
    }



    @GetMapping("/size")
    public ResponseEntity<Integer> findAllByBatchNumSize(@RequestParam(name = "batchNum") String batchNum){
        return ResponseEntity.status(HttpStatus.OK).body(oibtService.findAllByBatchNum(batchNum).size());
    }

    @GetMapping("/itemCode")
    public ResponseEntity<List<String>> findAllItemCodeByBatchNum(@RequestParam(name = "batchNum") String batchNum){
        return ResponseEntity.status(HttpStatus.OK).body(oibtService.findAllItemCodeByBatchNum(batchNum));
    }


}
