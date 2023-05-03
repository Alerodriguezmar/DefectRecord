package com.Safra.defectrecord.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public interface ReportService {


    void generatedReportByFabricSupplier(String supplier) throws IOException;

    void generatedReportByFabricSupplierAndBetweenDate(LocalDateTime startDate , LocalDateTime endDate, String supplier) throws IOException;
}
