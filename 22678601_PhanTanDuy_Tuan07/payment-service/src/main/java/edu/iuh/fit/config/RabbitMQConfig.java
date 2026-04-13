package edu.iuh.fit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "movie_ticket_exchange";

    // Tên các Queue
    public static final String QUEUE_BOOKING_CREATED = "booking_created_queue";
    public static final String QUEUE_PAYMENT_COMPLETED = "payment_completed_queue";
    public static final String QUEUE_BOOKING_FAILED = "booking_failed_queue";

    // Routing keys
    public static final String ROUTING_KEY_BOOKING_CREATED = "booking.created";
    public static final String ROUTING_KEY_PAYMENT_COMPLETED = "payment.completed";
    public static final String ROUTING_KEY_BOOKING_FAILED = "booking.failed";

    // Khởi tạo Exchange (Topic hoặc Direct)
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    // Khởi tạo các Queues
    @Bean
    public Queue bookingCreatedQueue() {
        return new Queue(QUEUE_BOOKING_CREATED, true);
    }

    @Bean
    public Queue paymentCompletedQueue() {
        return new Queue(QUEUE_PAYMENT_COMPLETED, true);
    }

    @Bean
    public Queue bookingFailedQueue() {
        return new Queue(QUEUE_BOOKING_FAILED, true);
    }

    // Binding Queue với Exchange thông qua Routing Key
    @Bean
    public Binding bindingBookingCreated(Queue bookingCreatedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookingCreatedQueue).to(exchange).with(ROUTING_KEY_BOOKING_CREATED);
    }

    @Bean
    public Binding bindingPaymentCompleted(Queue paymentCompletedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(paymentCompletedQueue).to(exchange).with(ROUTING_KEY_PAYMENT_COMPLETED);
    }

    @Bean
    public Binding bindingBookingFailed(Queue bookingFailedQueue, DirectExchange exchange) {
        return BindingBuilder.bind(bookingFailedQueue).to(exchange).with(ROUTING_KEY_BOOKING_FAILED);
    }

    // Cấu hình chuyển đổi Message sang JSON (thay vì Java Serialize mặc định)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
