package edu.iuh.fit.service;

import edu.iuh.fit.config.RabbitMQConfig;
import edu.iuh.fit.dto.BookingCreatedEvent;
import edu.iuh.fit.dto.PaymentEvent;
import edu.iuh.fit.entity.Payment;
import edu.iuh.fit.entity.PaymentStatus;
import edu.iuh.fit.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentAndNotificationService {

    private final PaymentRepository paymentRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    // ----------------------------------------------------
    // CHỨC NĂNG 1: PAYMENT SERVICE
    // ----------------------------------------------------
    // Lắng nghe sự kiện BOOKING CREATED [cite: 176]
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOKING_CREATED)
    public void processPayment(BookingCreatedEvent event) {
        log.info("Nhận yêu cầu thanh toán cho Booking ID: {}", event.getBookingId());

        Payment payment = new Payment();
        payment.setBookingId(event.getBookingId());
        payment.setAmount(event.getTotalAmount());
        payment.setPaymentMethod("BANKING");

        // Xử lý: Random success/fail [cite: 177] (Giả lập tỉ lệ thành công 80%)
        boolean isSuccess = random.nextInt(100) < 80;

        if (isSuccess) {
            payment.setStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);

            // Publish event PAYMENT COMPLETED [cite: 178, 179]
            PaymentEvent paymentEvent = new PaymentEvent(event.getBookingId(), "COMPLETED", "Thanh toán thành công");
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_PAYMENT_COMPLETED, paymentEvent);

            log.info("Đã gửi event PAYMENT_COMPLETED cho Booking ID: {}", event.getBookingId());
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);

            // Publish event BOOKING FAILED [cite: 178, 179]
            PaymentEvent failedEvent = new PaymentEvent(event.getBookingId(), "FAILED", "Tài khoản không đủ số dư");
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY_BOOKING_FAILED, failedEvent);

            log.info("Đã gửi event BOOKING_FAILED cho Booking ID: {}", event.getBookingId());
        }
    }

    // ----------------------------------------------------
    // CHỨC NĂNG 2: NOTIFICATION SERVICE
    // ----------------------------------------------------
    // Lắng nghe sự kiện PAYMENT_COMPLETED [cite: 181]
    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAYMENT_COMPLETED)
    public void sendNotification(PaymentEvent event) {
        // Output: "Booking #123 thành công!" [cite: 182]
        log.info("=========================================");
        log.info("🔔 THÔNG BÁO: Booking #{} thành công!", event.getBookingId());
        log.info("=========================================");
    }

    // Lắng nghe sự kiện BOOKING_FAILED để thông báo lỗi (Tuỳ chọn)
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOKING_FAILED)
    public void sendFailureNotification(PaymentEvent event) {
        log.error("=========================================");
        log.error("❌ THÔNG BÁO: Booking #{} thất bại. Lý do: {}", event.getBookingId(), event.getMessage());
        log.error("=========================================");
    }
}
