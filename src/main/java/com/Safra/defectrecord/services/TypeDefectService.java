package com.Safra.defectrecord.services;

import com.Safra.defectrecord.entities.FabricReport;
import com.Safra.defectrecord.entities.TypeDefect;

import java.util.List;

public interface TypeDefectService {

    TypeDefect findById(String id);

    TypeDefect create(TypeDefect typeDefect);

    TypeDefect update(TypeDefect typeDefect);

    List<TypeDefect> createAll(List<TypeDefect> typeDefects);

    List<TypeDefect> findAll();

    void delete(String id);
}
