package edu.iuh.fit.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookingCreatedEvent {
    private Long bookingId;
    private Long userId;
    private BigDecimal totalAmount;
}
