package com.pe.shopathome.validators;

import com.pe.shopathome.entity.Order;
import com.pe.shopathome.entity.PaymentAccount;
import com.pe.shopathome.exceptions.ValidateServiceException;

public class PaymentAccountValidator {

    public static void save(PaymentAccount paymentAccount) {

        if(paymentAccount.getOrder() == null ) {
            throw new ValidateServiceException("La orden es requerida");
        }

        if(paymentAccount.getCardNumber() <= 16) {
            throw new ValidateServiceException("El numero es muy largo (max 16)");
        }

        if(paymentAccount.getCardHolder() ==  null) {
            throw new ValidateServiceException("El nombre es requerido");
        }

        if(paymentAccount.getCvv() < 3) {
            throw new ValidateServiceException("El cvv es requerido");
        }

        //if(paymentAccount.getExpires() ==null) {
        //    throw new ValidateServiceException("Falta la fecha de expiracion");
        //}
    }

}
