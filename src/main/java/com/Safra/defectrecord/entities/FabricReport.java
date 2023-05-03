package com.Safra.defectrecord.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricReport {
    @Id
    private String id;
    private LocalDateTime creationDate;
    private FabricSupplier fabricSupplier;
    private Double quantityAffected;
    private TypeDefect typeDefect;
    private String batchNum;
    private List<String> imagesUrl;
    private String comment;
}
