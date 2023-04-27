package com.Safra.defectrecord.repositories;


import com.Safra.defectrecord.entities.OIBT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OIBTRepository extends JpaRepository<OIBT,String> {

    String consult = """
                        
             select distinct
                             bt.ItemCode,
                             bt.ItemName,
                             bt.BatchNum,
                             CONCAT(bt.ItemCode,'.',bt.BatchNum) Identifier
                       from OIBT AS bt
                             INNER JOIN OITM as tm ON bt.ItemCode = tm.ItemCode
                       where  U_GrupoProd = '06'
                             AND tm.ItemCode Like 'I_%%'
                             AND bt.BatchNum = :batchNum
            """;

    @Query(nativeQuery = true, value = consult)
    List<OIBT> findAllByBatchNum(@Param("batchNum") String whsCode);

}
