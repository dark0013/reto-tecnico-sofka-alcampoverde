package com.alcampoverde.ms_customer.infrastructure.in.rest.controller;


import com.alcampoverde.ms_customer.infrastructure.in.rest.dto.CustomerDto;
import com.alcampoverde.ms_customer.infrastructure.in.rest.handler.CustomerHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CustomerHandler customerHandler;
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        customerHandler = mock(CustomerHandler.class);
        customerController = new CustomerController(customerHandler);
    }

    @Test
    void testFinAllCustomer() {
       
        List<CustomerDto> customers = Arrays.asList(
                new CustomerDto() {{ setId(1); setName("Alice"); }},
                new CustomerDto() {{ setId(2); setName("Bob"); }}
        );
        when(customerHandler.findAllCustomers()).thenReturn(customers);

        
        ResponseEntity<List<CustomerDto>> response = customerController.finAllCustomer();

        
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(customerHandler, times(1)).findAllCustomers();
    }

    @Test
    void testFindCustomerById() {
       
        CustomerDto customer = new CustomerDto();
        customer.setId(1);
        customer.setName("Alice");
        when(customerHandler.findCustomerById(1)).thenReturn(customer);

        
        ResponseEntity<CustomerDto> response = customerController.findCustomerById(1);

        
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Alice", response.getBody().getName());
        verify(customerHandler, times(1)).findCustomerById(1);
    }
    
    @Test
    void testUpdateCustomer() {
       
        CustomerDto input = new CustomerDto();
        input.setId(1);
        input.setName("Alice Updated");
        when(customerHandler.updateCustomer(input)).thenReturn(input);

        
        ResponseEntity<CustomerDto> response = customerController.updateCustomer(input);

        
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Alice Updated", response.getBody().getName());
        verify(customerHandler, times(1)).updateCustomer(input);
    }

    @Test
    void testDeleteCustomer() {
       
        doNothing().when(customerHandler).deleteCustomer(1);

        
        ResponseEntity<Void> response = customerController.deleteCustomer(1);

        
        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());
        verify(customerHandler, times(1)).deleteCustomer(1);
    }
}