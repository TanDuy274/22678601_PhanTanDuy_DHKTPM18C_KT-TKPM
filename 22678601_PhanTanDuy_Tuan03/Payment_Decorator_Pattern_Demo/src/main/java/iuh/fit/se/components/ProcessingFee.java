package iuh.fit.se.components;

import iuh.fit.se.decorator.PaymentDecorator;
import iuh.fit.se.interfaces.Payment;

public class ProcessingFee extends PaymentDecorator {
    public ProcessingFee(Payment p) {
        super(p);
    }

    public double getAmount() {
        return super.getAmount() + 2.0;
    } // Cộng thêm phí 2$

    @Override
    public void process() {

    }
}
