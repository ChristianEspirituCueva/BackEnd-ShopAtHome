package com.pe.shopathome.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="payment_accounts")
public class PaymentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="card_number", nullable = false, length = 16)
    private Long cardNumber;

    @Column(name="card_holder", nullable = false)
    private String cardHolder;

    //@Column(name="expires", nullable = false)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //private Date expires;

    @Column(name="cvv", nullable = false, length = 3)
    private Integer cvv;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;
}
