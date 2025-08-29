package com.alcampoverde.ms_transactions.infraestructure.out.messaging;

import com.alcampoverde.ms_transactions.domain.port.out.IRequestMessagePort;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
public class RequestMessageAdapter implements IRequestMessagePort {


    @Autowired
    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;

    @Value("${app.kafka.request-topic}")
    private String requestTopic;

    @Value("${app.kafka.reply-topic}")
    private String replyTopic;


    @Override
    public String sendMessage(String message) {
        String resp = null;
        try {
            resp = sendAndRecived(message);
        } catch (Exception e) {
            return "false";
        }
        return resp;
    }


    private String sendAndRecived(String solicitud) throws ExecutionException, InterruptedException, TimeoutException {
        ProducerRecord<String, String> record = new ProducerRecord<>(requestTopic, solicitud);
        record.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));
        RequestReplyFuture<String, String, String> future = replyingKafkaTemplate.sendAndReceive(record);
        SendResult<String, String> sendResult = future.getSendFuture().get(10, TimeUnit.SECONDS);
        ConsumerRecord<String, String> consumerRecord = future.get(10, TimeUnit.SECONDS);
        return consumerRecord.value();
    }
}
