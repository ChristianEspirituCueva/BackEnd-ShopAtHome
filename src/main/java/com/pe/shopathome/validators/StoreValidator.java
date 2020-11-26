package com.pe.shopathome.validators;

import com.pe.shopathome.entity.Store;
import com.pe.shopathome.exceptions.ValidateServiceException;

public class StoreValidator {

    public static void validate(Store store) {

        if(store.getName() == null || store.getName().trim().isEmpty()) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(store.getName().length() > 100) {
            throw new ValidateServiceException("El nombre es muy largo (max 100)");
        }

        if(store.getCity() == null) {
            throw new ValidateServiceException("La dirección es requerida");
        }

        if(store.getCountry() == null) {
            throw new ValidateServiceException("El pais es requerido");
        }

        if(store.getPhone() == null) {
            throw new ValidateServiceException("El telefono es requerido");
        }

        if(store.getRUC() == null) {
            throw new ValidateServiceException("El RUC es requerido");
        }
    }

}
