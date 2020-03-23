package pl.krzysztofurban.springmessagingexample.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Exchange ordersExchange() {
        return new DirectExchange("orders_exchange");
    }

    @Bean
    public Queue ordersQueue() {
        return new Queue("queue");
    }

    @Bean
    public Queue messagesQueue() {
        return new Queue("notification");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(ordersQueue())
                .to(ordersExchange())
                .with("orders.*")
                .noargs();
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(messagesQueue())
                .to(ordersExchange())
                .with("notification.*")
                .noargs();
    }
}
