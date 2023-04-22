package com.Safra.defectrecord.services.Impl;

import com.Safra.defectrecord.entities.TypeDefect;
import com.Safra.defectrecord.repositories.TypeDefectRepository;
import com.Safra.defectrecord.services.TypeDefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDefectServiceImpl implements TypeDefectService {


    @Autowired
    private TypeDefectRepository typeDefectRepository;


    @Override
    public TypeDefect findById(String id) {
        return typeDefectRepository.findById(id).orElseThrow();
    }

    @Override
    public TypeDefect create(TypeDefect typeDefect) {
        return typeDefectRepository.save(typeDefect);
    }

    @Override
    public TypeDefect update(TypeDefect typeDefect) {
        return typeDefectRepository.save(typeDefect);
    }

    @Override
    public List<TypeDefect> createAll(List<TypeDefect> typeDefects) {
        return typeDefectRepository.saveAll(typeDefects);
    }

    @Override
    public List<TypeDefect> findAll() {
        return typeDefectRepository.findAll();
    }

    @Override
    public void delete(String id) {
        var typeDefectByDelete = typeDefectRepository.findById(id).orElseThrow();
        typeDefectRepository.delete(typeDefectByDelete);
    }
}
