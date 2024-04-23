package com.babywatchers.notificationservice;

import com.babywatchers.notificationservice.messagebus.MessageBus;
import com.babywatchers.notificationservice.messagebus.RabbitMQ;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationserviceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NotificationserviceApplication.class, args);

        MessageBus broker = new RabbitMQ();

        int maxTries = 5;
        for (int count = 0; count <= maxTries; count++) {
            try {
                broker.connect("localhost");
                break;
            } catch (Exception e) {
                if (count == maxTries) {
                    throw new Exception("Cannot connect to RabbitMQ. Shutting down.");
                }
                int sleepTime = 500 * (int) Math.pow(2, count);
                Thread.sleep(sleepTime);
                System.out.println("Retrying connection...");
            }
        }

        broker.subscribeToTopic("1");
    }
}
