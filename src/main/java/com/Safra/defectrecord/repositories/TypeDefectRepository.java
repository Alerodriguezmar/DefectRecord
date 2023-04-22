package com.Safra.defectrecord.repositories;

import com.Safra.defectrecord.entities.TypeDefect;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TypeDefectRepository extends MongoRepository<TypeDefect,String> {
}
