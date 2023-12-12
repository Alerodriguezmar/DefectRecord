package com.Safra.defectrecord.repositories;

import com.Safra.defectrecord.entities.TypeDefect;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TypeDefectRepository extends MongoRepository<TypeDefect,String> {

    List<TypeDefect> findAllByArea(String area);
}
