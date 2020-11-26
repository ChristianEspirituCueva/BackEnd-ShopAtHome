package com.pe.shopathome.dto;

import com.pe.shopathome.entity.Order;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAccountDTO {
    private Long id;
    private Long cardNumber;
    private String cardHolder;
    //private Date expires;
    private Integer cvv;
    private OrderDTO order;
}
