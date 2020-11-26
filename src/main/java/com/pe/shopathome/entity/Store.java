package com.pe.shopathome.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="RUC", nullable = false)
    private Long RUC;
    @Column(name="phone", nullable = false)
    private Long phone;
    @Column(name="country", nullable = false)
    private String country;
    @Column(name="city", nullable = false)
    private String city;
    @Column(name="address", nullable = false)
    private String address;

    //@ManyToOne
    //@JoinColumn(name="product_id", updatable = false)
    //private Product product;

}
