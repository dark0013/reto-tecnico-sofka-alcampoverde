package com.alcampoverde.ms_customer.infrastructure.out.persistence.repository;


import com.alcampoverde.ms_customer.infrastructure.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerEntity,Integer> {
}
