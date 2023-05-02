package com.Safra.defectrecord.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReportService {


    void generatedReportByFabricSupplier(String supplier) throws IOException;
}
