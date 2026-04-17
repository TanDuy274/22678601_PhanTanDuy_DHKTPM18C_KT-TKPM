package edu.iuh.fit.service;

import edu.iuh.fit.config.RabbitMQConfig;
import edu.iuh.fit.dto.BookingCreatedEvent;
import edu.iuh.fit.dto.PaymentEvent;
import edu.iuh.fit.dto.UserRegisteredEvent;
import edu.iuh.fit.entity.Payment;
import edu.iuh.fit.entity.PaymentStatus;
import edu.iuh.fit.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentAndNotificationService {

    private final PaymentRepository paymentRepository;
    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    // =========================================================================
    // 1. PAYMENT SERVICE - XỬ LÝ THANH TOÁN
    // =========================================================================

    /**
     * Lắng nghe event BOOKING_CREATED từ Booking Service
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOKING)
    @Transactional
    public void processPayment(BookingCreatedEvent event) {
        try {
            // THÊM MỚI: Check NULL để chặn lỗi "Poison Pill" (Dữ liệu rỗng làm crash app)
            if (event == null || event.getBookingId() == null || event.getTotalAmount() == null) {
                log.error("❌ Dữ liệu nhận được từ RabbitMQ không hợp lệ (null). Hủy bỏ xử lý tin nhắn này!");
                return; // Kết thúc sớm, không quăng Exception để RabbitMQ xóa tin nhắn rác này đi
            }

            log.info("🛒 Bắt đầu xử lý thanh toán cho Booking ID: {}", event.getBookingId());

            // Khởi tạo thông tin thanh toán
            Payment payment = new Payment();
            payment.setBookingId(event.getBookingId());
            payment.setAmount(event.getTotalAmount());
            payment.setPaymentMethod("BANKING");

            // Giả lập xử lý thanh toán: Random success/fail (Tỉ lệ thành công 70%)
            boolean isPaymentSuccessful = random.nextInt(100) < 70;

            if (isPaymentSuccessful) {
                handleSuccessfulPayment(payment, event.getBookingId());
            } else {
                handleFailedPayment(payment, event.getBookingId());
            }

        } catch (Exception e) {
            // THÊM MỚI: Bọc Try-Catch để nếu có lỗi (ví dụ rớt DB), RabbitMQ sẽ không bị lặp vô tận
            log.error("💥 Lỗi nghiêm trọng khi xử lý payment cho Booking ID: {}. Chi tiết: {}",
                    (event != null ? event.getBookingId() : "unknown"), e.getMessage());
        }
    }

    private void handleSuccessfulPayment(Payment payment, Long bookingId) {
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

        // Publish event: PAYMENT COMPLETED
        PaymentEvent successEvent = new PaymentEvent(bookingId, "COMPLETED", "Thanh toán thành công");
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_PAYMENT_COMPLETED,
                successEvent
        );
        log.info("✅ Thanh toán thành công. Đã publish event PAYMENT_COMPLETED cho Booking ID: {}", bookingId);
    }

    private void handleFailedPayment(Payment payment, Long bookingId) {
        payment.setStatus(PaymentStatus.FAILED);
        paymentRepository.save(payment);

        // Publish event: BOOKING FAILED
        PaymentEvent failedEvent = new PaymentEvent(bookingId, "FAILED", "Thanh toán thất bại do lỗi thẻ");
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY_BOOKING_FAILED,
                failedEvent
        );
        log.warn("❌ Thanh toán thất bại. Đã publish event BOOKING_FAILED cho Booking ID: {}", bookingId);
    }

    // =========================================================================
    // 2. NOTIFICATION SERVICE - XỬ LÝ THÔNG BÁO
    // =========================================================================

    /**
     * Lắng nghe event PAYMENT_COMPLETED để gửi thông báo thành công
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_PAYMENT_COMPLETED)
    public void sendSuccessNotification(PaymentEvent event) {
        log.info("=========================================");
        log.info("🔔 THÔNG BÁO: Booking #{} thành công!", event.getBookingId());
        log.info("=========================================");
    }

    /**
     * Lắng nghe event BOOKING_FAILED để gửi thông báo thất bại
     */
    @RabbitListener(queues = RabbitMQConfig.QUEUE_BOOKING_FAILED)
    public void sendFailureNotification(PaymentEvent event) {
        log.error("=========================================");
        log.error("🔕 THÔNG BÁO: Booking #{} thất bại. Chi tiết: {}", event.getBookingId(), event.getMessage());
        log.error("=========================================");
    }

//    lắng nghe user register

    @RabbitListener(queues = RabbitMQConfig.QUEUE_USER_REGISTERED) // Nhớ dùng hằng số mới
    public void sendWelcomeNotification(UserRegisteredEvent event) {
        try {
            if (event == null || event.getId() == null) {
                log.warn("Nhận được event đăng ký nhưng dữ liệu trống!");
                return;
            }

            log.info("=========================================");
            log.info("🎉 THÔNG BÁO: Chào mừng User '{}' (ID: {}) đã gia nhập hệ thống Movie Ticket!",
                    (event.getUserName() != null ? event.getUserName() : "Khách"),
                    event.getId());
            log.info("=========================================");

        } catch (Exception e) {
            log.error("Lỗi khi xử lý thông báo User Registered: {}", e.getMessage());
        }
    }
}