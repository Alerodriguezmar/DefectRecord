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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
}
