package com.babywatchers.notificationservice.messagebus;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMQ implements IMessageBus {
    Connection connection;
    final private String graphExchange = "graphdata";

    public void connect(String connectionString) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(connectionString);
        connection = factory.newConnection();
    }

    public void subscribeToTopic(String topicName) throws Exception {
        Channel channel = connection.createChannel();

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, graphExchange, topicName);

        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Received message from topic " + delivery.getEnvelope().getRoutingKey() + ": " + message);
        };

        channel.basicConsume(queueName, true, callback, consumerTag -> {});
    }

}
