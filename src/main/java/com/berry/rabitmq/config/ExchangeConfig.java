package com.berry.rabitmq.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {
  
  @Value("${rabbitmq.exchange.name}")
  private String firstExchangeName;
  
  @Value("${rabbitmq.exchange.name2}")
  private String secondExchangeName;
  
  /**
   * Simple way of building an exchange.
   * @return
   */
  @Bean
  Exchange firstExchange() {
    return new TopicExchange(firstExchangeName);
  }
  
  /**
   * Declarative way of building an exchange.
   * @return
   */
  @Bean
  Exchange secondExchange() {
    return ExchangeBuilder.topicExchange(secondExchangeName)
                          .durable(true)
                          .autoDelete()
                          .internal()
                          .build();
  }

}
