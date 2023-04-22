package com.Safra.defectrecord.services.Impl;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.repositories.FabricReportRepository;
import com.Safra.defectrecord.services.FTPService;
import com.Safra.defectrecord.services.FabricReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class FabricReportServiceImpl implements FabricReportService {


    @Autowired
    private FabricReportRepository fabricReportRepository;

    @Autowired
    private FTPService ftpService;


    @Override
    public FabricReport findById(String id) {
        return fabricReportRepository.findById(id).orElseThrow();
    }

    @Override
    public FabricReport create(FabricReport fabricReport) {
        fabricReport.setCreationDate(Instant.now());
        return fabricReportRepository.save(fabricReport);
    }

    @Override
    public FabricReport createFile(FabricReport fabricReport, MultipartFile[] file) throws IOException {
        fabricReport.setCreationDate(Instant.now());
        fabricReport.setImagesUrl(ftpService.uploadFiles(file));
        return fabricReportRepository.save(fabricReport);
    }

    @Override
    public FabricReport update(FabricReport fabricReport) {
        return fabricReportRepository.save(fabricReport);
    }

    @Override
    public List<FabricReport> createAll(List<FabricReport> fabricReport) {
        return fabricReportRepository.saveAll(fabricReport);
    }

    @Override
    public List<FabricReport> findAll() {
        return fabricReportRepository.findAll();
    }

    @Override
    public void delete(String id) {

    }
}
