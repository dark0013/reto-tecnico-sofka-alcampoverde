package com.alcampoverde.ms_customer.infrastructure.in.rest.configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.alcampoverde.ms_customer.application.service",
        includeFilters = {
                @ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.REGEX, pattern = "^.+Service")
        } )
public class BeanConfiguration {
}
