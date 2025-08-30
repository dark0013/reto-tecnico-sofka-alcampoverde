package com.alcampoverde.ms_customer.infrastructure.in.rest.mapper;

import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.infrastructure.in.rest.dto.CustomerDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:17:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ICustomerMapperImpl implements ICustomerMapper {

    @Override
    public CustomerDto toDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setGender( customer.getGender() );
        customerDto.setAge( customer.getAge() );
        customerDto.setIdentification( customer.getIdentification() );
        customerDto.setAddress( customer.getAddress() );
        customerDto.setTelephone( customer.getTelephone() );

        return customerDto;
    }

    @Override
    public Customer toDomain(CustomerDto customer) {
        if ( customer == null ) {
            return null;
        }

        Customer customer1 = new Customer();

        customer1.setId( customer.getId() );
        customer1.setName( customer.getName() );
        customer1.setGender( customer.getGender() );
        customer1.setAge( customer.getAge() );
        customer1.setIdentification( customer.getIdentification() );
        customer1.setAddress( customer.getAddress() );
        customer1.setTelephone( customer.getTelephone() );

        return customer1;
    }
}
