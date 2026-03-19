package iuh.fit.se;

import iuh.fit.se.concreteStates.CancelledOrder;
import iuh.fit.se.context.OrderContext;
import iuh.fit.se.interfaces.OrderState;
import iuh.fit.se.models.NewOrder;

public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        // Luồng xử lý đơn hàng tự động chuyển trạng thái
        System.out.println("--- Bắt đầu quy trình đơn hàng ---");
        order.apply(); // Chạy NewOrder -> Chuyển sang Processing
        order.apply(); // Chạy Processing -> Chuyển sang Shipped
        order.apply(); // Chạy Shipped

        System.out.println("\n--- Trường hợp đơn hàng bị Hủy ---");
        OrderContext cancelledOrder = new OrderContext();
        cancelledOrder.setState(new CancelledOrder());
        cancelledOrder.apply();
    }
}