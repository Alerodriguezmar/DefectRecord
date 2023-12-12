package com.Safra.defectrecord.services.Impl;

import com.Safra.defectrecord.entities.FabricSupplier;
import com.Safra.defectrecord.repositories.FabricSupplierRepository;
import com.Safra.defectrecord.services.FabricSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FabricSupplierServiceImpl implements FabricSupplierService {

    @Autowired
    private FabricSupplierRepository fabricSupplierRepository;

    @Override
    public FabricSupplier findFabricSupplierById(String id) {
        return fabricSupplierRepository.findById(id).orElseThrow();
    }

    @Override
    public FabricSupplier create(FabricSupplier fabricSupplier) {
        return fabricSupplierRepository.save(fabricSupplier);
    }

    @Override
    public FabricSupplier update(FabricSupplier fabricSupplier) {
        return fabricSupplierRepository.save(fabricSupplier);
    }

    @Override
    public void delete(String id) {
        var fabricToDelete = fabricSupplierRepository.findById(id).orElseThrow();
        fabricSupplierRepository.delete(fabricToDelete);
    }

    @Override
    public List<FabricSupplier> createAll( List<FabricSupplier> fabricSuppliers) {
        return fabricSupplierRepository.saveAll(fabricSuppliers);
    }

    @Override
    public List<FabricSupplier> findAllFabricSupplier() {
        return fabricSupplierRepository.findAll();
    }

    @Override
    public List<String> findAllFabricSupplierName() {
        return fabricSupplierRepository
                .findAll()
                .stream()
                .map(FabricSupplier::getSupplier)
                .distinct()
                .toList();
    }
}
