package com.Safra.defectrecord.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Esta clase representa las telas(itemCode and Item Description) y su respectivo proveedor
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FabricSupplier {

    /**
     * ItemCode de la tela
     * identificador unico tela-proveedor.
     * @Example I100002235
     */
    @Id
    private  String itemCode;

    /**
     * referencia de la tela
     * @Example Blackout Lusso Blanco 1.83 Mt
     */
    private String reference;

    /**
     * El nombre de el proveedor de la tela
     * @Example BUTTLER
     */
    private String supplier;
}
