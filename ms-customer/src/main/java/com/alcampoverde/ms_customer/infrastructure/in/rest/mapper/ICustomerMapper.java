package com.alcampoverde.ms_customer.infrastructure.in.rest.mapper;


import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.infrastructure.in.rest.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper {
    CustomerDto toDto(Customer customer);

    Customer toDomain(CustomerDto customer);
}


