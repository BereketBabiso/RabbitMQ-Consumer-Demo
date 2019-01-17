package com.berry.rabitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabitmqDemoConsumerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RabitmqDemoConsumerApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
   System.out.println("Application just started.");
    
  }

}

