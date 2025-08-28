package com.alcampoverde.ms_customer.infrastructure.in.mesagge;

import com.alcampoverde.ms_customer.domain.model.Customer;
import com.alcampoverde.ms_customer.domain.port.in.ICustomerServicePort;
import com.alcampoverde.ms_customer.domain.port.in.IlistenerMessagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListenerMessageAdapter implements IlistenerMessagePort {

    private final ICustomerServicePort customerService;

    @KafkaListener(topics = "${app.kafka.request-topic}")
    @SendTo
    @Override
    public String listenMessage(String message) {
        if (message == null || message.isEmpty()) {
            return "false";
        }
        Customer customer = customerService.findCustomerById(Integer.valueOf(message));
        if (customer == null) {
            return "false";
        }
        return "true";
    }

}