package com.pe.shopathome.services;

import com.pe.shopathome.converters.StoreConverter;
import com.pe.shopathome.entity.Store;
import com.pe.shopathome.exceptions.GeneralServiceException;
import com.pe.shopathome.exceptions.NoDataFoundException;
import com.pe.shopathome.exceptions.ValidateServiceException;
import com.pe.shopathome.repository.StoreRepository;
import com.pe.shopathome.validators.StoreValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Slf4j
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public List<Store> findAll(Pageable page){
        try {
            return storeRepository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public List<Store> findAll(){
        try {
            List<Store> stores = storeRepository.findAll();
            return stores;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }
    public Store findById(Long id) {
        try {
            return storeRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La tienda no existe"));
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public void delete(Long id) {
        try {
            Store store = storeRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La tienda no existe"));

            storeRepository.delete(store);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }


    @Transactional
    public Store save(Store store) {
        try {
            StoreValidator.validate(store);

            //Product product;

            if (store.getId() == null) {
                //store.setProduct(product);

                return storeRepository.save(store);
            }

            Store exitStore = storeRepository.findById(store.getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe la tiendita"));

            exitStore.setName(store.getName());
            exitStore.setRUC(store.getRUC());
            exitStore.setPhone(store.getPhone());
            exitStore.setCountry(store.getCountry());
            exitStore.setCity(store.getCity());
            exitStore.setAddress(store.getAddress());

            storeRepository.save(exitStore);
            return exitStore;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }

    }

}
