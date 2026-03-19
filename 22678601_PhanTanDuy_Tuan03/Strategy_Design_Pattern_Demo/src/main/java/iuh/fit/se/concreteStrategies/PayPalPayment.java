package iuh.fit.se.concreteStrategies;

import iuh.fit.se.interfaces.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Thanh toán " + amount + "đ qua PayPal."); }
}
