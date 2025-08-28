package com.alcampoverde.ms_customer.infrastructure.out.persistence.mapper;

import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.infrastructure.out.persistence.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerRepositoryMapper {
    Customer toDomain(CustomerEntity customerEntity);
    CustomerEntity toEntity(Customer customer);
}
