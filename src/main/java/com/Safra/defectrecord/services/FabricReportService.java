package com.Safra.defectrecord.services;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.entities.FabricSupplier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FabricReportService {

    FabricReport findById(String id);

    FabricReport create(FabricReport fabricReport);

    FabricReport createFile(FabricReport fabricReport, MultipartFile[] file) throws IOException;

    FabricReport update(FabricReport fabricReport);

    List<FabricReport> createAll(List<FabricReport> fabricReport);

    List<FabricReport> findAll();

    void delete(String id);

}