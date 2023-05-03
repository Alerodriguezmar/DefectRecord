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
import java.time.LocalDateTime;
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
        fabricReport.setCreationDate(LocalDateTime.now());
        return fabricReportRepository.save(fabricReport);
    }

    @Override
    public FabricReport createFile(FabricReport fabricReport, MultipartFile[] file) throws IOException {
        fabricReport.setCreationDate(LocalDateTime.now());
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

    @Override
    public List<FabricReport> findAllByFabricSupplierItemCode(String itemCode) {
        return fabricReportRepository.findAllByFabricSupplierItemCode(itemCode);
    }

    @Override
    public List<FabricReport> findAllByFabricSupplierSupplier(String supplier) {
        return fabricReportRepository.findAllByFabricSupplierSupplier(supplier);
    }

    @Override
    public List<FabricReport> findAllByFabricTypeDefectDescription(String description) {
        return fabricReportRepository.findAllByTypeDefectDescription(description);
    }

    @Override
    public Integer countByFabricSupplierSupplier(String supplier) {
        return fabricReportRepository.countByFabricSupplierSupplier(supplier);
    }

    @Override
    public List<FabricReport> findByCreationDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return fabricReportRepository.findByCreationDateBetween(startDate, endDate);
    }

    @Override
    public List<FabricReport> findByCreationDateBetweenAndFabricSupplierSupplier(LocalDateTime startDate, LocalDateTime endDate, String supplier) {
        return fabricReportRepository.findByCreationDateBetweenAndFabricSupplierSupplier(startDate, endDate, supplier);
    }
}
