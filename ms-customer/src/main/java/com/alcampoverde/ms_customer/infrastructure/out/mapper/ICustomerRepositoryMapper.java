package com.alcampoverde.ms_customer.infrastructure.out.mapper;

import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.infrastructure.out.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerRepositoryMapper {
    Customer toDomain(CustomerEntity customerEntity);
    CustomerEntity toEntity(Customer customer);
}
