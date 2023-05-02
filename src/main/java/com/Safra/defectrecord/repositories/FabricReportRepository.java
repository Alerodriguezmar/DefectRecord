package com.Safra.defectrecord.repositories;

import com.Safra.defectrecord.entities.FabricReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FabricReportRepository extends MongoRepository<FabricReport,String> {

    List<FabricReport> findAllByFabricSupplierItemCode(String itemCode);

    List<FabricReport> findAllByFabricSupplierSupplier(String supplier);

    List<FabricReport> findAllByTypeDefectDescription(String description);

    Integer countByFabricSupplierSupplier(String supplier);
}
