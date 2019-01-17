package com.berry.rabitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  
  @Value("${rabbitmq.queque.name}")
  private String firstQueueName;
  
  @Value("${rabbitmq.queue.name2}")
  private String secondQueueName;
  
  @Value("${rabbitmq.host.name}")
  private String rabbitMqHostName;
  
  @Value("${rabbitmq.user.name}")
  private String rabbitMqUserName;
  
  @Value("${rabbitmq.password}")
  private String rabbitMqPassword;
  
  /**
   * This is the new Queue required to be created by Java program and attached
   * to the RabbitMQConsumer, which is the listener class.
   * @return
   */
  @Bean 
  Queue testQueue() {
    return new Queue(firstQueueName,true);
  }
  
  /**
   * Second and recommended way of creating and configuring rabbitMQ.
   * @return
   */
  @Bean
  Queue testQueue2() {
    return QueueBuilder.durable("secondTestQueue")
                       .autoDelete().exclusive().build();
  }
  
  /**
   * This is the connection factory to the message broker.
   * @return
   */
  @Bean
  ConnectionFactory connectionFactory() {
    CachingConnectionFactory connection = new CachingConnectionFactory();
    connection.setHost(rabbitMqHostName);
    connection.setUsername(rabbitMqUserName);
    connection.setPassword(rabbitMqPassword);
    
    return connection;
  }
  
  /**
   * This is the container that brings the connection factory, the queue and the
   * consumer or listener.
   * @return
   */
  @Bean
  public MessageListenerContainer container() {
    SimpleMessageListenerContainer listenerContainer = 
        new SimpleMessageListenerContainer();
    listenerContainer.setConnectionFactory(connectionFactory());
    listenerContainer.setQueues(testQueue());
    listenerContainer.setMessageListener(new RabbbitMQConsumer());
    
    return listenerContainer;
  }
  
  
  @Bean
  Exchange testExchange() {
    return ExchangeBuilder.topicExchange("TestTopicExchange")
        .durable(false)
        .autoDelete()
        .build();
  }
  
  @Bean
  Binding binding() {
    return new Binding(firstQueueName, Binding.DestinationType.QUEUE, "TestTopicExchange", "testkey", null);
  }

}
