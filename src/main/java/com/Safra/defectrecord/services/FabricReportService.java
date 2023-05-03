package com.Safra.defectrecord.services;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.entities.FabricSupplier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public interface FabricReportService {

    FabricReport findById(String id);

    FabricReport create(FabricReport fabricReport);

    FabricReport createFile(FabricReport fabricReport, MultipartFile[] file) throws IOException;

    FabricReport update(FabricReport fabricReport);

    List<FabricReport> createAll(List<FabricReport> fabricReport);

    List<FabricReport> findAll();

    void delete(String id);

    List<FabricReport> findAllByFabricSupplierItemCode(String itemCode);

    List<FabricReport> findAllByFabricSupplierSupplier(String supplier);

    List<FabricReport> findAllByFabricTypeDefectDescription(String description);

    Integer countByFabricSupplierSupplier(String supplier);


    List<FabricReport> findByCreationDateBetween(LocalDateTime startDate , LocalDateTime endDate);


    List<FabricReport> findByCreationDateBetweenAndFabricSupplierSupplier(LocalDateTime startDate , LocalDateTime endDate,String supplier);

}
