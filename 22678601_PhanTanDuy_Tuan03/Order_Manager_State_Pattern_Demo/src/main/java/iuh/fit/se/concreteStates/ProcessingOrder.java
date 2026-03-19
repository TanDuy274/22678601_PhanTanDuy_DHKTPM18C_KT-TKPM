package iuh.fit.se.concreteStates;

import iuh.fit.se.context.OrderContext;
import iuh.fit.se.interfaces.OrderState;

public class ProcessingOrder implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Đang xử lý: Đóng gói và vận chuyển...");
        context.setState(new ShippedOrder());
    }
}
