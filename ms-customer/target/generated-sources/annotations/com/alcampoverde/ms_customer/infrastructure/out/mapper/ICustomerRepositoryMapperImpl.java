package com.alcampoverde.ms_customer.infrastructure.out.mapper;

import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.infrastructure.out.entity.CustomerEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-30T05:17:40-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ICustomerRepositoryMapperImpl implements ICustomerRepositoryMapper {

    @Override
    public Customer toDomain(CustomerEntity customerEntity) {
        if ( customerEntity == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerEntity.getId() );
        customer.setName( customerEntity.getName() );
        customer.setGender( customerEntity.getGender() );
        customer.setAge( customerEntity.getAge() );
        customer.setIdentification( customerEntity.getIdentification() );
        customer.setAddress( customerEntity.getAddress() );
        customer.setTelephone( customerEntity.getTelephone() );

        return customer;
    }

    @Override
    public CustomerEntity toEntity(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId( customer.getId() );
        customerEntity.setName( customer.getName() );
        customerEntity.setGender( customer.getGender() );
        customerEntity.setAge( customer.getAge() );
        customerEntity.setIdentification( customer.getIdentification() );
        customerEntity.setAddress( customer.getAddress() );
        customerEntity.setTelephone( customer.getTelephone() );

        return customerEntity;
    }
}
