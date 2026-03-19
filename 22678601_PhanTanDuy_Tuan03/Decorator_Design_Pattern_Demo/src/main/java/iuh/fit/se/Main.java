package iuh.fit.se;

import iuh.fit.se.component.MilkTea;
import iuh.fit.se.concreteComponent.BasicMilkTea;
import iuh.fit.se.concreteDecorators.Jelly;
import iuh.fit.se.concreteDecorators.Pearl;

public class Main {
    public static void main(String[] args) {
        MilkTea myOrder = new BasicMilkTea();
        myOrder = new Pearl(myOrder);
        myOrder = new Jelly(myOrder);

        System.out.println("Đơn hàng: " + myOrder.getDescription());
        System.out.println("Tổng tiền: " + myOrder.getCost() + "k");
    }
}