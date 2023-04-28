package com.Safra.defectrecord.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OIBT")
public class OIBT {


    @Column(name = "ItemCode")
    private String  itemCode;

    @Column(name = "BatchNum")
    private String  batchNum;
    @Id
    @Column(name = "Identifier")
    private String  identifier;
    @Column(name = "ItemName")
    private String itemName;

}
