package com.Safra.defectrecord.controllers;

import com.Safra.defectrecord.entities.FabricSupplier;
import com.Safra.defectrecord.services.FabricSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabricSupplier")
public class FabricSupplierController {



    @Autowired
    private FabricSupplierService fabricSupplierService;

    /**
     * Obtiene todos los FabricSupplier.
     *
     * @return Lista de todos los FabricSupplier
     */
    @GetMapping("")
    public ResponseEntity <List<FabricSupplier>> findAllFabricSupplier() {
        return ResponseEntity.status(HttpStatus.OK).body(fabricSupplierService.findAllFabricSupplier());
    }

    /**
     * Obtiene un FabricSupplier por su identificador.
     *
     * @param id Identificador del FabricSupplier
     * @return FabricSupplier con el identificador especificado
     */
    @GetMapping("/{id}")
    public ResponseEntity<FabricSupplier> findById(@PathVariable String id){

        return  ResponseEntity.status(HttpStatus.OK).body(fabricSupplierService.findFabricSupplierById(id));
    }

    /**
     * Crea un nuevo fabricSupplier.
     *
     * @param fabricSupplier Nuevo fabricSupplier a crear
     * @return El fabricSupplier recién creado
     */
    @PostMapping("")
    public ResponseEntity<FabricSupplier> create (@RequestBody FabricSupplier fabricSupplier) {
        return ResponseEntity.status(HttpStatus.OK).body(fabricSupplierService.create(fabricSupplier));
    }

    /**
     * Actualiza un usuario existente.
     *
     * @param id Identificador del fabricSupplier a actualizar
     * @param fabricSupplier Usuario actualizado
     * @return El fabricSupplier actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<FabricSupplier> update(@PathVariable Long id, @RequestBody FabricSupplier fabricSupplier){
       return ResponseEntity.status(HttpStatus.OK).body(fabricSupplierService.update(fabricSupplier));
    }

    /**
     * Elimina un fabricSupplier por su identificador.
     *
     * @param id Identificador del fabricSupplier a eliminar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>  delete(@PathVariable String id){
        fabricSupplierService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    /**
     * Crea  nuevos fabricSupplier.
     *
     * @param fabricSuppliers Nuevos fabricSupplier a crear
     * @return Los fabricSuppliers recién creados
     */
    @PostMapping("/saveAll")
    public ResponseEntity<List<FabricSupplier>> create (@RequestBody List<FabricSupplier> fabricSuppliers) {
        return ResponseEntity.status(HttpStatus.OK).body(fabricSupplierService.createAll(fabricSuppliers));
    }
}
