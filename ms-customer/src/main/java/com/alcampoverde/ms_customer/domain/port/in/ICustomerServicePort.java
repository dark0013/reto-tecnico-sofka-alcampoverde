package com.alcampoverde.ms_customer.domain.port.in;

import com.alcampoverde.ms_customer.domain.model.Customer;

import java.util.List;

public interface ICustomerServicePort {
    List<Customer> findAllCustomers();

    Customer findCustomerById(Integer id);

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(Integer id);
}
