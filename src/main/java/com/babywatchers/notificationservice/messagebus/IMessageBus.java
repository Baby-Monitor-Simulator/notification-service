package com.babywatchers.notificationservice.messagebus;

public interface IMessageBus {
    public void connect(String connectionString) throws Exception;
    public void subscribeToTopic(String topicName) throws Exception;
}
