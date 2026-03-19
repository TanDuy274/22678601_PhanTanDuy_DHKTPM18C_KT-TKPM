package iuh.fit.se.concreteStates;

import iuh.fit.se.context.OrderContext;
import iuh.fit.se.interfaces.OrderState;

public class CancelledOrder implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Đã hủy: Đang hoàn tiền cho khách hàng.");
    }
}
