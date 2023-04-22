package com.Safra.defectrecord.repositories;

import com.Safra.defectrecord.entities.FabricSupplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Respositorio para la entidad FabricSupplier
 */
@Repository
public interface FabricSupplierRepository extends MongoRepository<FabricSupplier,String> {
}
