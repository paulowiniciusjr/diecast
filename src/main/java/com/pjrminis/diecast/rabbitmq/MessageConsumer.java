package com.pjrminis.diecast.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {
    @RabbitListener(queues = "minhaFila")
    public void receive(String message) {
        System.out.println("Recebido: " + message);
    }
}