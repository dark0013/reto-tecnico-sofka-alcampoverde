package com.alcampoverde.ms_customer.infrastructure.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_adm_customer")
@Data
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String telephone;
}