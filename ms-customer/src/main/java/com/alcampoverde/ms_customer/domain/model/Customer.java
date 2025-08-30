package com.alcampoverde.ms_customer.domain.model;

import lombok.Data;

@Data
public class Customer {

    private Integer id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String telephone;
}
