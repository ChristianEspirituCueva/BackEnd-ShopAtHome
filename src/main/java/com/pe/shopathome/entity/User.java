package com.pe.shopathome.entity;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "username",length = 30,nullable = false)
    private String username;
    @Column(name = "password",length = 150,nullable = false)
    private String password;

    @OneToOne(mappedBy = "user")
    private Profile profile;
}
