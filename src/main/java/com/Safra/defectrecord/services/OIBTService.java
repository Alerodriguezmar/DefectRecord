package com.Safra.defectrecord.services;


import com.Safra.defectrecord.entities.OIBT;

import java.util.List;

public interface OIBTService {

    List<OIBT> findAllByBatchNum(String batchNum);

    List<String> findAllItemCodeByBatchNum(String batchNum);

    List<OIBT> findAllByName(String name);

    List<String> findAllItemCodeByName(String name);
}
