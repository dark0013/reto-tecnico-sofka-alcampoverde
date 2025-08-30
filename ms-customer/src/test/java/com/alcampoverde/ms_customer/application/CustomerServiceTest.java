package com.alcampoverde.ms_customer.application;
import com.alcampoverde.ms_customer.application.exception.CustomerNotFoundException;
import com.alcampoverde.ms_customer.application.service.CustomerService;
import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.domain.port.out.ICustomerRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private ICustomerRepositoryPort customerRepositoryPort;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepositoryPort = mock(ICustomerRepositoryPort.class);
        customerService = new CustomerService(customerRepositoryPort);
    }

    @Test
    void findAllCustomers_ShouldReturnCustomers_WhenCustomersExist() {
        List<Customer> mockCustomers = Arrays.asList(
                createCustomer(1, "John Doe"),
                createCustomer(2, "Jane Doe")
        );
        when(customerRepositoryPort.findAllCustomers()).thenReturn(mockCustomers);

        List<Customer> result = customerService.findAllCustomers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(customerRepositoryPort, times(1)).findAllCustomers();
    }

    @Test
    void findAllCustomers_ShouldThrowException_WhenNoCustomersExist() {
        when(customerRepositoryPort.findAllCustomers()).thenReturn(Collections.emptyList());

        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.findAllCustomers());
        assertEquals("No customers found", exception.getMessage());
        verify(customerRepositoryPort, times(1)).findAllCustomers();
    }

    @Test
    void findCustomerById_ShouldReturnCustomer_WhenCustomerExists() {
        Customer mockCustomer = createCustomer(1, "John Doe");
        when(customerRepositoryPort.findCustomerById(1)).thenReturn(Optional.of(mockCustomer));

        Customer result = customerService.findCustomerById(1);

        
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepositoryPort, times(1)).findCustomerById(1);
    }

    @Test
    void findCustomerById_ShouldThrowException_WhenCustomerDoesNotExist() {
        
        when(customerRepositoryPort.findCustomerById(1)).thenReturn(Optional.empty());

      
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
                () -> customerService.findCustomerById(1));
        assertEquals("NO CUSTOMERS FOUND", exception.getMessage());
        verify(customerRepositoryPort, times(1)).findCustomerById(1);
    }


    private Customer createCustomer(int id, String name) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setGender("M");
        customer.setAge(30);
        customer.setIdentification("1234567890");
        customer.setAddress("Some Address");
        customer.setTelephone("+123456789");
        return customer;
    }
}