package com.pe.shopathome.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private Long DNI;
}
