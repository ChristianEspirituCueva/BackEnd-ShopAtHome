package com.pe.shopathome.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role",nullable=false)
    private String role;

    @Column(name="firts_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name="country", nullable = false)
    private String country;

    @Column(name="city", nullable = false)
    private String city;

    //Relationships
    //User One To One
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
