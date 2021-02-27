package com.example.springjmsdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    // a queue is a destination point for JmsConfig; only one listener can receive a message stored in the queue
    public static final String MY_QUEUE = "The Greetings queue";

    public static final String SEND_RECEIVE_QUEUE = "Send and receive queue";

    @Bean
    public MessageConverter messageConverter(){
        // utilise the Jackson JSON library
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        // convert the (JSON) message to a JMS text message and then to an object
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }
}
