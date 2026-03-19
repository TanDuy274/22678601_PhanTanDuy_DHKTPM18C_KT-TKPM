package iuh.fit.se.context;

import iuh.fit.se.interfaces.OrderState;
import iuh.fit.se.models.NewOrder;

public class OrderContext {
    private OrderState state = new NewOrder();
    public void setState(OrderState state) {
        this.state = state;
    }

    public void apply() {
        state.handle(this);
    }
}
