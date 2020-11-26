package com.pe.shopathome.validators;

import com.pe.shopathome.entity.Product;
import com.pe.shopathome.entity.Profile;
import com.pe.shopathome.exceptions.ValidateServiceException;

public class ProfileValidator {
    public static void save(Profile profile) {

        if(profile.getCity() == null || profile.getCity().trim().isEmpty()) {
            throw new ValidateServiceException("La ciudad es requerida");
        }

        if(profile.getCountry()== null || profile.getCity().trim().isEmpty())  {
            throw new ValidateServiceException("El pais es requerido");
        }

        if(profile.getFirstName() == null || profile.getFirstName().trim().isEmpty()
                || profile.getLastName() == null || profile.getLastName().trim().isEmpty()) {
            throw new ValidateServiceException("Sus datos son requerido");
        }

        if(profile.getPhone().trim().isEmpty() || profile.getPhone()==null ) {
            throw new ValidateServiceException("El numero es incorrecto");
        }
    }
}
