package com.example.springjmsdemo;

import org.apache.activemq.artemis.core.config.Configuration;
import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJmsDemoApplication {

    public static void main(String[] args) throws Exception {

        // embedded server with configuration runs, though initialisation errors are thrown
        Configuration configuration = new ConfigurationImpl();

        configuration
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("in-vm", "vm://0")
                .addAcceptorConfiguration("tcp", "tcp://127.0.0.1:61616");

        EmbeddedActiveMQ server = new EmbeddedActiveMQ();
        server.setConfiguration(configuration);
        server.start();

        SpringApplication.run(SpringJmsDemoApplication.class, args);
    }

}
