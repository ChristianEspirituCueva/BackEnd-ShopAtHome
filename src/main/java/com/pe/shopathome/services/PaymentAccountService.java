package com.pe.shopathome.services;

import com.pe.shopathome.entity.Order;
import com.pe.shopathome.entity.PaymentAccount;
import com.pe.shopathome.exceptions.GeneralServiceException;
import com.pe.shopathome.exceptions.NoDataFoundException;
import com.pe.shopathome.exceptions.ValidateServiceException;
import com.pe.shopathome.repository.OrderRepository;
import com.pe.shopathome.repository.PaymentAccountRepository;
import com.pe.shopathome.validators.PaymentAccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PaymentAccountService {

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private OrderRepository orderRepository;

    public List<PaymentAccount> findAll(Pageable page){
        try {
            return paymentAccountRepository.findAll(page).toList();
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public PaymentAccount findById(Long id) {
        try {
            return paymentAccountRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La transaccion no existe"));
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
            PaymentAccount paymentAccount = paymentAccountRepository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("La orden no existe"));

            paymentAccountRepository.delete(paymentAccount);

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    public List<PaymentAccount> findAll(){
        try {
            List<PaymentAccount> paymentAccounts = paymentAccountRepository.findAll();
            return paymentAccounts;
        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

    @Transactional
    public PaymentAccount save(PaymentAccount paymentAccount) {
        try {
            PaymentAccountValidator.save(paymentAccount);

            //User user= UserPrincipal.getCurrentUser();
            //Create Order
            if(paymentAccount.getId() == null) {
                //order.setUser(user);
                PaymentAccount newPay = paymentAccountRepository.save(paymentAccount);
                return newPay;
            }

            //Update Order
            PaymentAccount savePay = paymentAccountRepository.findById(paymentAccount.getId())
                    .orElseThrow(() -> new NoDataFoundException("La transaccion no existe"));
            //RegDate no se cambia, se mantiene la de creacion
            Order order = orderRepository.findById(paymentAccount.getOrder().getId())
                    .orElseThrow(() -> new NoDataFoundException("No existe la orden " + paymentAccount.getOrder().getId()));
            savePay.setOrder(order);
            savePay.setCardNumber(paymentAccount.getCardNumber());
            savePay.setCardHolder(paymentAccount.getCardHolder());
            savePay.setCvv(paymentAccount.getCvv());
            //savePay.setExpires(paymentAccount.getExpires());

            paymentAccountRepository.save(savePay);
            return savePay;

        } catch (ValidateServiceException | NoDataFoundException e) {
            log.info(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new GeneralServiceException(e.getMessage(), e);
        }
    }

}
