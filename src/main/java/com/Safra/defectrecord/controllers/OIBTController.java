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

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("OIBT")

public class OIBTController {



    @Autowired
    private OIBTService oibtService;


    @GetMapping()
    public ResponseEntity<List<OIBT>> findAllByBatchNum(@RequestParam(name = "batchNum" ,required = false) String batchNum,@RequestParam(name = "name" ,required = false) String name){



        List<OIBT> oibts = new ArrayList<>();

        if (batchNum != null && !batchNum.isEmpty()) {
            oibts =  oibtService.findAllByBatchNum(batchNum);
        }

        if (name != null && !name.isEmpty()) {
            oibts =  oibtService.findAllByName(name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(oibts);
    }



    @GetMapping("/size")
    public ResponseEntity<Integer> findAllByBatchNumSize(@RequestParam(name = "batchNum" ,required = false) String batchNum,@RequestParam(name = "name" ,required = false) String name){

        List<OIBT> oibts = new ArrayList<>();

        if (batchNum != null && !batchNum.isEmpty()) {
            oibts =  oibtService.findAllByBatchNum(batchNum);
        }

        if (name != null && !name.isEmpty()) {
            oibts =  oibtService.findAllByName(name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(oibts.size());
    }

    @GetMapping("/itemCode")
    public ResponseEntity<List<String>> findAllItemCodeByBatchNum(@RequestParam(name = "batchNum" ,required = false) String batchNum,@RequestParam(name = "name" ,required = false) String name){

        List<String> oibts = new ArrayList<>();

        if (batchNum != null && !batchNum.isEmpty()) {
            oibts =  oibtService.findAllItemCodeByBatchNum(batchNum);
        }

        if (name != null && !name.isEmpty()) {
            oibts =  oibtService.findAllItemCodeByName(name);
        }

        return ResponseEntity.status(HttpStatus.OK).body(oibts);
    }


}
