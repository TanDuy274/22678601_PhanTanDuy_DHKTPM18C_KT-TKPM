package edu.iuh.fit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Tên Exchange dùng chung cho toàn hệ thống
    public static final String EXCHANGE = "movie_ticket_exchange";

    // 1. Queue và Routing Key cho Booking (Payment Service cần lắng nghe)
    public static final String QUEUE_BOOKING = "booking_created_queue";
    public static final String ROUTING_KEY_BOOKING = "booking.created";

    // 2. Queue và Routing Key cho Payment Thành Công (Payment Service publish)
    public static final String QUEUE_PAYMENT_COMPLETED = "payment_completed_queue";
    public static final String ROUTING_KEY_PAYMENT_COMPLETED = "payment.completed";

    // 3. Queue và Routing Key cho Payment Thất Bại (Payment Service publish)
    public static final String QUEUE_BOOKING_FAILED = "booking_failed_queue";
    public static final String ROUTING_KEY_BOOKING_FAILED = "booking.failed";

//    Khai báo Exchange và Queue để khớp với User Service
    public static final String EXCHANGE_USER = "user-exchange";
    public static final String QUEUE_USER_REGISTERED = "user-registration-queue"; // Đổi tên cho giống bên User
    public static final String ROUTING_KEY_USER_REGISTERED = "user.registered";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    // --- Khởi tạo các Queue ---

    @Bean
    public Queue bookingQueue() {
        return new Queue(QUEUE_BOOKING);
    }

    @Bean
    public Queue paymentCompletedQueue() {
        return new Queue(QUEUE_PAYMENT_COMPLETED);
    }

    @Bean
    public Queue bookingFailedQueue() {
        return new Queue(QUEUE_BOOKING_FAILED);
    }

    // --- Khởi tạo Bindings (Liên kết Queue với Exchange qua Routing Key) ---

    @Bean
    public Binding bindingBooking(Queue bookingQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bookingQueue).to(exchange).with(ROUTING_KEY_BOOKING);
    }

    @Bean
    public Binding bindingPaymentCompleted(Queue paymentCompletedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(paymentCompletedQueue).to(exchange).with(ROUTING_KEY_PAYMENT_COMPLETED);
    }

    @Bean
    public Binding bindingBookingFailed(Queue bookingFailedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(bookingFailedQueue).to(exchange).with(ROUTING_KEY_BOOKING_FAILED);
    }

    @Bean
    public DirectExchange userExchange() {
        return new DirectExchange(EXCHANGE_USER);
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue(QUEUE_USER_REGISTERED, true);
    }

    @Bean
    public Binding bindingUserRegistered(Queue userRegisteredQueue, DirectExchange userExchange) {
        return BindingBuilder.bind(userRegisteredQueue).to(userExchange).with(ROUTING_KEY_USER_REGISTERED);
    }

    // --- Cấu hình Converter ---

    @Bean
    public MessageConverter converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);
        return converter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
