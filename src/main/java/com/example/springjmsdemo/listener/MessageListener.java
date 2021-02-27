package com.example.springjmsdemo.listener;

import com.example.springjmsdemo.config.JmsConfig;
import com.example.springjmsdemo.model.SimpleMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    // deserialise with @Payload; @Headers to get message headers
    // @JmsListener: listen out for the My_QUEUE name (and then execute this method's block)
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload SimpleMessage simpleMessage,
                       @Headers MessageHeaders messageHeaders, Message message){

        System.out.println("Message received on the queue, " + JmsConfig.MY_QUEUE);

        // simpleMessage toString() handled by Lombok
        System.out.println(simpleMessage);

        // use the debugger to learn more about the properties of messageHeaders, a JSON, and message parameters, a Java object
        // (these are not actually needed here)
        System.out.println("The messageHeaders parameter: " + messageHeaders);
        System.out.println("The message parameter: " + message);
    }
}
