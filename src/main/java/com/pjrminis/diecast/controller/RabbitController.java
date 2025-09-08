package com.pjrminis.diecast.controller;

import com.pjrminis.diecast.rabbitmq.MessageProducer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rabbit")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class RabbitController {
    private final MessageProducer producer;

    public RabbitController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public void send(@RequestParam String msg) {
        producer.send(msg);
    }
}