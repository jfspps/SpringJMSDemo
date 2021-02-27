package com.example.springjmsdemo.sender;

import com.example.springjmsdemo.config.JmsConfig;
import com.example.springjmsdemo.model.SimpleMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class SendersMessage {

    // handles pre-configured ActiveMQ server credentials and establish connection for SendersMessage
    private final JmsTemplate jmsTemplate;

    // 2000 msec
    @Scheduled(fixedRate = 2000)
    public void sendMessage(){

        // confirm SendersMessage is wired in
        System.out.println("Sending a message");

        // build the payload
        SimpleMessage message = SimpleMessage.builder()
                .id(UUID.randomUUID())
                .message("Howdy!")
                .build();

        // send message to the queue named MY_QUEUE (see MessageListener class)
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        System.out.println("Message sent");
    }
}
