package edu.iuh.fit.controller;

import edu.iuh.fit.config.RabbitMQConfig;
import edu.iuh.fit.dto.BookingCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Random;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    @PostMapping("/booking")
    public ResponseEntity<String> mockBookingCreated() {
        // 1. Tạo một sự kiện giả lập (giống như Order Service vừa tạo đơn xong)
        BookingCreatedEvent mockEvent = new BookingCreatedEvent();
        mockEvent.setBookingId(Math.abs(random.nextLong() % 10000)); // Random ID
        mockEvent.setUserId(1L);
        mockEvent.setTotalAmount(new BigDecimal("150000.00")); // Giá vé ví dụ 150k

        // 2. Bắn sự kiện vào RabbitMQ (vào đúng Exchange và Routing Key)
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY_BOOKING_CREATED,
                mockEvent
        );

        return ResponseEntity.ok("Đã bắn sự kiện giả lập tạo Booking ID: " + mockEvent.getBookingId() + " vào RabbitMQ!");
    }
}
