package com.example.springmongo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class QueueConfig {

    @Bean(name = "product.queue")
    public Queue productQueue() {
        return new Queue("product.queue", false);
    }
}
