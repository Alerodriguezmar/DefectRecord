package com.Safra.defectrecord.services.Impl;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.services.FTPService;
import com.Safra.defectrecord.services.FabricReportService;
import com.Safra.defectrecord.services.ReportService;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.policy.PictureRenderPolicy;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ReportServiceImpl  implements ReportService {


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

    @Autowired
    private FabricReportService fabricReportService;

    @Autowired
    private FTPService ftpService;

    @Override
    public void generatedReportByFabricSupplier(String supplier) throws IOException {

        List<FabricReport> fabricReportList = fabricReportService.findAllByFabricSupplierSupplier(supplier);

        XWPFTemplate template = XWPFTemplate.compile("/app/ReportModel.docx").render(
                new HashMap<String, Object>(){{

                    put("supplier", supplier);

                    List<Map<String, Object>> reports = new ArrayList<>();

                   for(FabricReport fabricReport: fabricReportList){
                       List<Map<String, Object>> evidence = new ArrayList<>();

                        Map<String, Object> dataReport = new HashMap<>();

                        dataReport.put("batchNum",fabricReport.getBatchNum());

                        dataReport.put("defect",fabricReport.getTypeDefect().getDescription());

                        dataReport.put("quantity",fabricReport.getQuantityAffected()+" Mts");

                        for(String ulr: fabricReport.getImagesUrl()){

                            Map<String, Object> dataReportImg = new HashMap<>();

                            dataReportImg.put("img", Pictures.ofStream(ftpService.downloadFile(ulr,null), PictureType.JPEG)
                                    .size(100, 120).create());
                            evidence.add(dataReportImg);
                        }
                        dataReport.put("evidence", evidence);

                        reports.add(dataReport);
                    }

                   put("fabricReport", reports);
                }});
        template.writeAndClose(new FileOutputStream("output.docx"));

        

    }

    @Override
    public void generatedReportByFabricSupplierAndBetweenDate(LocalDateTime startDate, LocalDateTime endDate, String supplier) throws IOException {

        System.out.println("Initial Report");

        List<FabricReport> fabricReportList = new ArrayList<>();

        if(supplier.equals("All") || supplier.equals("Todos")){
            fabricReportList = fabricReportService.findByCreationDateBetween(startDate,endDate);
           supplier = "Todos";
        } else {
            fabricReportList  = fabricReportService.findByCreationDateBetweenAndFabricSupplierSupplier(startDate,endDate,supplier);
        }

        List<FabricReport> finalFabricReportList = fabricReportList;

        System.out.println("TotalDatos: "+ fabricReportList.size());

        String finalSupplier = supplier;
       // XWPFTemplate template = XWPFTemplate.compile("ReportModel.docx").render(
        List<FabricReport> finalFabricReportList1 = fabricReportList;
        XWPFTemplate template = XWPFTemplate.compile("/app/ReportModel.docx").render(

              //  XWPFTemplate template = XWPFTemplate.compile("ReportModel.docx").render(

                new HashMap<String, Object>(){{

                    put("supplier", finalSupplier);

                    put("generatedDate", LocalDate.now());



                   var resume =  finalFabricReportList1.stream().collect(Collectors.groupingBy(FabricReport::getFabricSupplier));

                    List<Map<String, Object>> resumen = new ArrayList<>();
                   resume.forEach((fabricSupplier, fabricReports) -> {
                       Map<String, Object> resumenSpec = new HashMap<>();
                       var countTotal = fabricReports.stream().mapToDouble(FabricReport::getQuantityAffected).sum();
                       resumenSpec.put("prove",fabricSupplier.getSupplier());
                       resumenSpec.put("item",fabricSupplier.getItemCode());
                       resumenSpec.put("name",fabricSupplier.getReference());
                       resumenSpec.put("count",fabricReports.size());
                       resumenSpec.put("total",countTotal);
                       resumen.add(resumenSpec);
                   });

                    List<Map<String, Object>> reports = new ArrayList<>();

                    for(FabricReport fabricReport: finalFabricReportList){
                        List<Map<String, Object>> evidence = new ArrayList<>();

                        Map<String, Object> dataReport = new HashMap<>();

                        dataReport.put("batchNum",fabricReport.getBatchNum());

                        dataReport.put("defect",fabricReport.getTypeDefect().getDescription());

                        dataReport.put("quantity",fabricReport.getQuantityAffected()+" Mts");

                        dataReport.put("reference",fabricReport.getFabricSupplier().getReference());

                        dataReport.put("itemCode",fabricReport.getFabricSupplier().getItemCode());

                        dataReport.put("comments",fabricReport.getComment());

                        dataReport.put("supplierInfo",fabricReport.getFabricSupplier().getSupplier());

                        for(String ulr: fabricReport.getImagesUrl()){

                            Map<String, Object> dataReportImg = new HashMap<>();

                            dataReportImg.put("img", Pictures.ofStream(ftpService.downloadFile(ulr,null), PictureType.JPEG)
                                    .size(250, 250).create());
                            evidence.add(dataReportImg);
                        }
                        dataReport.put("evidence", evidence);

                        reports.add(dataReport);
                    }
                    put("resume",resumen);
                    put("fabricReport", reports);
                }});
        template.writeAndClose(new FileOutputStream("output.docx"));

    }
}
