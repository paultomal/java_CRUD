package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String password;

    @OneToOne
    @JoinColumn(name = "p_address", referencedColumnName = "id")
    private Address presentAddress;

    @OneToOne
    @JoinColumn(name = "pre_address", referencedColumnName = "id")
    private Address premanentAddress;
}
