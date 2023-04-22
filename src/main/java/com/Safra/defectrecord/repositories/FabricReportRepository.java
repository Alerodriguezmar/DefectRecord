package com.Safra.defectrecord.repositories;

import com.Safra.defectrecord.entities.FabricReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricReportRepository extends MongoRepository<FabricReport,String> {
}
