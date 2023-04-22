package com.Safra.defectrecord.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FTPService {


    String uploadFile(MultipartFile[] file) throws IOException;


    List<String> uploadFiles(MultipartFile[] file) throws IOException;
}
