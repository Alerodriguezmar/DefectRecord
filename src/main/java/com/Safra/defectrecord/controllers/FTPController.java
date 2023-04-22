package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.services.FTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ftp")
public class FTPController {

    @Autowired
    private FTPService ftpService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile[] file) throws IOException {

        String response = ftpService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}


