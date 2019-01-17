package com.berry.rabitmq;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQConfigTest {

  @Mock
  RabbitMQConfig rabbitMQConfig;
  
  @Test
  public void success_flow() {
    Queue q = new Queue("firstqueue", true);
    //when(rabbitMQConfig.testQueue()).thenReturn(q);
    
    Queue qq = rabbitMQConfig.testQueue();
    System.out.println(q.getName());
    Assert.assertNull(qq);
  }
}
