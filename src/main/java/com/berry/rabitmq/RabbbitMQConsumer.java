package com.berry.rabitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RabbbitMQConsumer implements MessageListener {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbbitMQConsumer.class);

  @Override
  public void onMessage(Message message) {
    
    LOGGER.info(new String(message.getBody()));
    
  }

}
