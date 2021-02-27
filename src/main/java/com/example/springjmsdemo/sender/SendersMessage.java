package com.example.springjmsdemo.sender;

import com.example.springjmsdemo.config.JmsConfig;
import com.example.springjmsdemo.model.SimpleMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
@AllArgsConstructor
public class SendersMessage {

    // handles pre-configured ActiveMQ server credentials and establish connection for SendersMessage
    private final JmsTemplate jmsTemplate;

    private final ObjectMapper objectMapper;

    // 10000 msec
    @Scheduled(fixedRate = 10000)
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

    // this sender sends a message and the subscriber immediately sends a reply to a different queue which this sender
    // subscribes to (and receives the reply)
    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {

        // confirm SendersMessage is wired in
        System.out.println("Sending a send-and-receive message");

        // build the payload
        SimpleMessage message = SimpleMessage.builder()
                .id(UUID.randomUUID())
                .message("Howdy! Did you get it?")
                .build();

        // send message to the queue named SEND_RECEIVE_QUEUE (see MessageListener class)
        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.SEND_RECEIVE_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message sendMessage = null;
                try {
                    sendMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    sendMessage.setStringProperty("_type", SimpleMessage.class.getName());

                    return sendMessage;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new JMSException("Problem processing JSON");
                }
            }
        });

        System.out.println("Send-and-receive message sent. Awaiting reply");
        System.out.println(receivedMsg.getBody(String.class));
    }
}
