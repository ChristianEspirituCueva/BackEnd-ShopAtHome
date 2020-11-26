package com.pe.shopathome.controller;

import com.pe.shopathome.converters.PaymentAccountConverter;
import com.pe.shopathome.dto.PaymentAccountDTO;
import com.pe.shopathome.dto.ProductDTO;
import com.pe.shopathome.entity.PaymentAccount;
import com.pe.shopathome.entity.Product;
import com.pe.shopathome.services.PaymentAccountService;
import com.pe.shopathome.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentAccountController {
    @Autowired
    private PaymentAccountService paymentAccountService;

    @Autowired
    private PaymentAccountConverter converter;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentAccountDTO> findById(@PathVariable("paymentId") Long paymentId) {
        PaymentAccount paymentAccount = paymentAccountService.findById(paymentId);
        PaymentAccountDTO paymentAccountDTO = converter.fromEntity(paymentAccount);
        return new WrapperResponse(true, "success", paymentAccountDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> delete(@PathVariable("paymentId") Long paymentId) {
        paymentAccountService.delete(paymentId);
        return new WrapperResponse(true, "success", null)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PaymentAccountDTO>> findAll(){
        List<PaymentAccount> payments = paymentAccountService.findAll();
        List<PaymentAccountDTO> dtoPayments = converter.fromEntity(payments);

        return new WrapperResponse(true, "success", dtoPayments)
                .createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentAccountDTO> create(@RequestBody PaymentAccountDTO paymentAccount) {
        PaymentAccount newPayment = paymentAccountService.save(converter.fromDTO(paymentAccount));
        PaymentAccountDTO paymentAccountDTO = converter.fromEntity(newPayment);

        return new WrapperResponse(true, "success", paymentAccountDTO)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentAccountDTO> update(@RequestBody PaymentAccountDTO paymentAccount) {
        PaymentAccount updatePaymentAccount = paymentAccountService.save(converter.fromDTO(paymentAccount));
        PaymentAccountDTO paymentAccountDTO = converter.fromEntity(updatePaymentAccount);

        return new WrapperResponse(true, "success", paymentAccountDTO)
                .createResponse(HttpStatus.OK);
    }
}
