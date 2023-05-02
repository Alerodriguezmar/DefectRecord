package com.Safra.defectrecord.services;

import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FTPService {


    String uploadFile(MultipartFile[] file) throws IOException;


    List<String> uploadFiles(MultipartFile[] file) throws IOException;

    InputStream downloadFile(String file,FTPSClient ftpsClient) throws IOException;

}
