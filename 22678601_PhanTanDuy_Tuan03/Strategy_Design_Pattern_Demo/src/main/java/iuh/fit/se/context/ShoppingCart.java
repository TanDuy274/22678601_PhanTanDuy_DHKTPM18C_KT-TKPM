package iuh.fit.se.context;

import iuh.fit.se.interfaces.PaymentStrategy;

public class ShoppingCart {
    private PaymentStrategy strategy;

    public void setPaymentMethod(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void checkout(int amount) {
        strategy.pay(amount);
    }
}
