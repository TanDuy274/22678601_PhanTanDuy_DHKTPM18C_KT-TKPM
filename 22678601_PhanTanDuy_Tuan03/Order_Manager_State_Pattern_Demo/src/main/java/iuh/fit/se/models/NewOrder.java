package iuh.fit.se.models;

import iuh.fit.se.concreteStates.ProcessingOrder;
import iuh.fit.se.context.OrderContext;
import iuh.fit.se.interfaces.OrderState;

public class NewOrder implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Đơn mới: Đang kiểm tra thông tin...");
        context.setState(new ProcessingOrder());
    }
}
