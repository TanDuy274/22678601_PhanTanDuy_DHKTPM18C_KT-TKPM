package iuh.fit.se.concreteStates;

import iuh.fit.se.context.OrderContext;
import iuh.fit.se.interfaces.OrderState;

public class ShippedOrder implements OrderState {
    public void handle(OrderContext context) {
        System.out.println("Đã giao: Cập nhật hệ thống thành công.");
    }
}
