package com.pe.shopathome.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String city;
    private UserDTO user;
}
