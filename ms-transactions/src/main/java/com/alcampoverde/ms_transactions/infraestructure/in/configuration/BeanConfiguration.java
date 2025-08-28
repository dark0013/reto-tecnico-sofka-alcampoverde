package com.alcampoverde.ms_transactions.infraestructure.in.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.alcampoverde.ms_transactions.application.service",
        includeFilters = {
                @ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.REGEX, pattern = "^.+Service")
        } )
public class BeanConfiguration {
}
