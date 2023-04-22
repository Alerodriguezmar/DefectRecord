package com.Safra.defectrecord.services.Impl;


import com.Safra.defectrecord.services.FTPService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FTPServiceImpl implements FTPService {

    @Value("${ftp.server}")
    private String server;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.path}")
    private String path;

    public String uploadFile(MultipartFile[] file1) throws IOException {


        FTPSClient ftpsClient = new FTPSClient();
        try {

            //Login ftp
            ftpsClient.connect(server, port);
            ftpsClient.login(username, password);

            //Success SSL/TLS
            ftpsClient.execPBSZ(0);
            ftpsClient.execPROT("P");

            //Config FTP
            ftpsClient.enterLocalPassiveMode();
            ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);

            for(MultipartFile file:file1){
                //Transform multipartFile in InputStream
                InputStream inputStream = file.getInputStream();

                //Extract name file
                String fileName = file.getOriginalFilename();

                System.out.println(fileName);

                //directory
                var directoryPath = path+fileName+"/";

                // Check if the directory exists
                boolean directoryExists = ftpsClient.changeWorkingDirectory(directoryPath);

                if (!directoryExists) {
                    // Create the directory if it doesn't exist
                    boolean directoryCreated = ftpsClient.makeDirectory(directoryPath);
                    if (directoryCreated) {
                        System.out.println("Directory created successfully.");
                    } else {
                        System.out.println("Failed to create directory.");
                    }
                } else {
                    System.out.println("Directory already exists.");
                }

                //save file in ftp
                boolean done = ftpsClient.storeFile(path+fileName+"/" + fileName, inputStream);

                //close stream
                inputStream.close();
                //check if saved
                if (done) {
                    //return "File uploaded successfully";
                } else {
                    //return "File upload failed";
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } finally {

            //disconnect FTP and logout
            try {
                if (ftpsClient.isConnected()) {
                    ftpsClient.logout();
                    ftpsClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "exit";
    }

    @Override
    public List<String> uploadFiles(MultipartFile[] file1) throws IOException {
        FTPSClient ftpsClient = new FTPSClient();

        List<String> urls = new ArrayList<>();
        try {

            //Login ftp
            ftpsClient.connect(server, port);
            ftpsClient.login(username, password);

            //Success SSL/TLS
            ftpsClient.execPBSZ(0);
            ftpsClient.execPROT("P");

            //Config FTP
            ftpsClient.enterLocalPassiveMode();
            ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);

            for(MultipartFile file:file1){
                //Transform multipartFile in InputStream
                InputStream inputStream = file.getInputStream();

                //Extract name file
                String fileName = file.getOriginalFilename();

                System.out.println(fileName);

                //directory
                var directoryPath = path+fileName+"/";

                // Check if the directory exists
                boolean directoryExists = ftpsClient.changeWorkingDirectory(directoryPath);

                if (!directoryExists) {
                    // Create the directory if it doesn't exist
                    boolean directoryCreated = ftpsClient.makeDirectory(directoryPath);
                    if (directoryCreated) {
                        System.out.println("Directory created successfully.");
                    } else {
                        System.out.println("Failed to create directory.");
                    }
                } else {
                    System.out.println("Directory already exists.");
                }

                //save file in ftp
                boolean done = ftpsClient.storeFile(path+fileName+"/" + fileName, inputStream);

                urls.add(path+fileName+"/" + fileName);
                //close stream
                inputStream.close();
                //check if saved
                if (done) {
                    //return "File uploaded successfully";
                } else {
                    //return "File upload failed";
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //disconnect FTP and logout
            try {
                if (ftpsClient.isConnected()) {
                    ftpsClient.logout();
                    ftpsClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return urls;
    }
}
