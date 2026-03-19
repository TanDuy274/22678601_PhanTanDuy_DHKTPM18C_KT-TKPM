package iuh.fit.se.decorator;

import iuh.fit.se.interfaces.Payment;

public abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;
    public PaymentDecorator(Payment p) {
        this.decoratedPayment = p;
    }

    public double getAmount() {
        return decoratedPayment.getAmount();
    }
}
