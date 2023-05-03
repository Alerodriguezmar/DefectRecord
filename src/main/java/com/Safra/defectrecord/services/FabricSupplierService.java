package com.Safra.defectrecord.services;


import com.Safra.defectrecord.entities.FabricSupplier;

import java.util.List;

/**
 * Interfaz de servicio para la entidad de FabricSupplier
 */
public interface FabricSupplierService {


    /**
     * Encuentra un FabricSupplier por su identificador.
     *
     * @param id El identificador del FabricSupplier.
     * @return El FabricSupplier con el identificador especificado.
     */
    FabricSupplier findFabricSupplierById(String id);


    /**
     * Crea un nuevo fabricSupplier con la información proporcionada.
     *
     * @param fabricSupplier La información del nuevo usuario.
     * @return El fabricSupplier creado.
     */
    FabricSupplier create(FabricSupplier fabricSupplier);


    /**
     * Actualiza la información de un FabricSupplier existente.
     *
     * @param fabricSupplier La información actualizada del fabricSupplier.
     * @return El FabricSupplier actualizado.
     */
    FabricSupplier update(FabricSupplier fabricSupplier);


    /**
     * Elimina un usuario existente.
     *
     * @param id El identificador del fabricSupplier a eliminar.
     */
    void delete(String id);


    /**
     * Crea nuevos fabricSupplier con la información proporcionada.
     *
     * @param fabricSuppliers La información de los nuevos fabricSuppliers.
     * @return Los fabricSuppliers creados.
     */
    List<FabricSupplier> createAll(List<FabricSupplier> fabricSuppliers);



    /**
     * Obtiene todos los  fabricSupplier almacenados.
     * @return Los fabricSuppliers obtenidos.
     */
    List<FabricSupplier> findAllFabricSupplier();


    List<String> findAllFabricSupplierName();

}
