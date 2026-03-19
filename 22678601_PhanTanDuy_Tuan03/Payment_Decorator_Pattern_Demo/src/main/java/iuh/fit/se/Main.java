package iuh.fit.se;

import iuh.fit.se.components.DiscountCode;
import iuh.fit.se.components.ProcessingFee;
import iuh.fit.se.concreteComponent.CreditCardPayment;
import iuh.fit.se.interfaces.Payment;

public class Main {
    public static void main(String[] args) {
        // 1. Thanh toán gốc 100$
        Payment basicPayment = new CreditCardPayment(100.0);
        System.out.println("Thanh toán gốc: " + basicPayment.getAmount() + "$");

        // 2. Thanh toán có thêm Phí xử lý (Cộng thêm 2$)
        Payment paymentWithFee = new ProcessingFee(basicPayment);
        System.out.println("Sau khi cộng phí xử lý: " + paymentWithFee.getAmount() + "$");

        // 3. Thanh toán có cả Phí xử lý VÀ Mã giảm giá (Trừ đi 5$)
        // "Bọc" cái đã có phí vào trong cái giảm giá
        Payment finalPayment = new DiscountCode(paymentWithFee);

        System.out.println("--- Hóa đơn cuối cùng ---");
        System.out.println("Mô tả: Thẻ tín dụng + Phí + Giảm giá");
        System.out.println("Tổng cộng: " + finalPayment.getAmount() + "$");
        finalPayment.process();
    }
}