package com.pe.shopathome.controller;



import com.pe.shopathome.converters.StoreConverter;
import com.pe.shopathome.dto.StoreDTO;
import com.pe.shopathome.entity.Store;
import com.pe.shopathome.services.StoreService;
import com.pe.shopathome.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreConverter storeConverter;

    @GetMapping
    public ResponseEntity<List<StoreDTO>> findAll(){
        List<Store> stores = storeService.findAll();
        List<StoreDTO> storeDTOS = storeConverter.fromEntity(stores);

        return new WrapperResponse(true, "success", storeDTOS)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WrapperResponse<StoreDTO>> findById(@PathVariable(name="id") Long id){
        Store store = storeService.findById(id);
        return new WrapperResponse<>(true, "success", storeConverter.fromEntity(store))
                .createResponse();
    }
    @PostMapping
    public ResponseEntity<StoreDTO> create(@RequestBody StoreDTO storeDTO) {
        Store newStore = storeService.save(storeConverter.fromDTO(storeDTO));
        return new WrapperResponse(true, "success", newStore)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StoreDTO> update(@RequestBody StoreDTO storeDTO) {
        Store newStore = storeService.save(storeConverter.fromDTO(storeDTO));
        return new WrapperResponse(true, "success", newStore)
                .createResponse(HttpStatus.CREATED);
    }


    @DeleteMapping("/stores/{storeId}")
    public ResponseEntity<?> delete(@PathVariable("storeId") Long storeId) {
        storeService.delete(storeId);
        return new WrapperResponse(true, "success", null)
                .createResponse(HttpStatus.OK);
    }






}
