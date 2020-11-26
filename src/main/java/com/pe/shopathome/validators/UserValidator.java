package com.pe.shopathome.validators;

import com.pe.shopathome.entity.User;
import com.pe.shopathome.exceptions.ValidateServiceException;

public class UserValidator {
    public static void validate(User user){
        if(user.getUsername()==null||user.getUsername().trim().isEmpty()){
            throw  new ValidateServiceException("El nombre de usuario es requerido");
        }
        if(user.getUsername().length()>30){
            throw  new ValidateServiceException("El nombre de usuario es muy largo");
        }
        if(user.getPassword()==null||user.getUsername().isEmpty()){
            throw  new ValidateServiceException("El password es requerido");
        }
        if(user.getPassword().length()>30){
            throw new ValidateServiceException("El password es muy largo");
        }
    }
}