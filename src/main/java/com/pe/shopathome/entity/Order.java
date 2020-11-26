package com.pe.shopathome.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderLine> lines;

    @Column(name="total", nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name="user_id", updatable = false)
    private User user;

    //@OneToOne(mappedBy = "order")
    //private PaymentAccount paymentAccount;
}
