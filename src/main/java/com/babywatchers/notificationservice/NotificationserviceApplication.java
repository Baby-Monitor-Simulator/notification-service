package com.babywatchers.notificationservice;

import com.babywatchers.notificationservice.messagebus.IMessageBus;
import com.babywatchers.notificationservice.messagebus.RabbitMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationserviceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NotificationserviceApplication.class, args);

        IMessageBus broker = new RabbitMQ();
        broker.connect("localhost");
        broker.subscribeToTopic("1");
    }

}
