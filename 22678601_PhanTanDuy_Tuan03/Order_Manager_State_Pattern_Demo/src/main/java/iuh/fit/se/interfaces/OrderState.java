package iuh.fit.se.interfaces;

import iuh.fit.se.context.OrderContext;

public interface OrderState {
    void handle(OrderContext context);
}
