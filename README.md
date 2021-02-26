# SpringJMSDemo

Spring Boot demo of JMS messaging. This project also uses an embedded server, with the following dependencies at the time of writing:

```xml
        <dependency>
            <groupId>org.apache.activemq</groupId>
            <artifactId>artemis-server</artifactId>
            <version>2.17.0</version>
        </dependency>

<!--        adding an embedded server  -->
        <dependency>
            <groupId>org.apache.activemq</groupId>
            <artifactId>artemis-jms-server</artifactId>
            <version>2.17.0</version>
        </dependency>
```
