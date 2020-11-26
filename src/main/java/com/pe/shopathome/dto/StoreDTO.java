package com.pe.shopathome.dto;

import com.pe.shopathome.entity.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {
    private Long id;
    private String name;
    private Long RUC;
    private Long phone;
    private String country;
    private String city;
    private String address;
    //private ProductDTO product;
}
