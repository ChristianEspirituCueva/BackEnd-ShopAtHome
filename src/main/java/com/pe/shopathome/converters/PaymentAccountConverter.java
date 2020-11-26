package com.pe.shopathome.converters;

import com.pe.shopathome.dto.OrderDTO;
import com.pe.shopathome.dto.PaymentAccountDTO;
import com.pe.shopathome.dto.ProductDTO;
import com.pe.shopathome.entity.Order;
import com.pe.shopathome.entity.PaymentAccount;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PaymentAccountConverter  extends AbstractConverter<PaymentAccount, PaymentAccountDTO>{
    private OrderConverter orderConverter;
    @Override
    public PaymentAccountDTO fromEntity(PaymentAccount entity) {
        if(entity == null) return null;
        return PaymentAccountDTO.builder()
                .id(entity.getId())
                .cardHolder(entity.getCardHolder())
                .cvv(entity.getCvv())
                .cardNumber(entity.getCardNumber())
                //.expires(entity.getExpires())
                .order(orderConverter.fromEntity(entity.getOrder()))
                .build();
    }

    @Override
    public PaymentAccount fromDTO(PaymentAccountDTO dto) {
        if(dto == null) return null;
        return PaymentAccount.builder()
                .id(dto.getId())
                .cardHolder(dto.getCardHolder())
                .cvv(dto.getCvv())
                .cardNumber(dto.getCardNumber())
                //.expires(dto.getExpires())
                .order(orderConverter.fromDTO(dto.getOrder()))
                .build();
    }
}
