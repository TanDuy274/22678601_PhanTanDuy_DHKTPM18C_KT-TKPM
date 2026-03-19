package iuh.fit.se.components;

import iuh.fit.se.decorator.PaymentDecorator;
import iuh.fit.se.interfaces.Payment;

public class DiscountCode extends PaymentDecorator {
    public DiscountCode(Payment p) {
        super(p);
    }

    public double getAmount() {
        return super.getAmount() - 5.0;
    } // Giảm 5$

    @Override
    public void process() {

    }
}
