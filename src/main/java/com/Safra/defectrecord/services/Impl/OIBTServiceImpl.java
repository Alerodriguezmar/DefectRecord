package com.Safra.defectrecord.services.Impl;


import com.Safra.defectrecord.entities.OIBT;
import com.Safra.defectrecord.repositories.OIBTRepository;
import com.Safra.defectrecord.services.OIBTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OIBTServiceImpl implements OIBTService {

    @Autowired
    private OIBTRepository oibtRepository;

    @Override
    public List<OIBT> findAllByBatchNum(String batchNum) {
        return oibtRepository.findAllByBatchNum(batchNum);
    }

    @Override
    public List<String> findAllItemCodeByBatchNum(String batchNum) {

        var oibts = oibtRepository.findAllByBatchNum(batchNum);

        return oibts.stream().map(OIBT::getItemCode).distinct().toList();
    }
}
