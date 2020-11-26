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
@Table(name="products")
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Column(name="price", nullable = false)
    private Double price;

    //@ManyToOne
    //@JoinColumn(name="category_id", updatable = false)
    //private Category category;

    //@Column(name="detail", nullable = false, length = 150)
    //private String detail;

}
