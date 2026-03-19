package iuh.fit.se;

import iuh.fit.se.concreteStrategies.LuxuryTaxStrategy;
import iuh.fit.se.concreteStrategies.VATStrategy;
import iuh.fit.se.context.Product;

public class Main {
    public static void main(String[] args) {
        // Sản phẩm thông thường với VAT 10%
        Product laptop = new Product("Laptop Dell", 20000000, new VATStrategy());
        System.out.println("Sản phẩm: Laptop");
        System.out.println("Tổng tiền (có VAT): " + laptop.getTotalPrice() + " VND");

        // Sản phẩm xa xỉ với Thuế đặc biệt 50%
        Product car = new Product("Siêu xe", 500000000, new LuxuryTaxStrategy());
        System.out.println("\nSản phẩm: Siêu xe");
        System.out.println("Tổng tiền (có thuế xa xỉ): " + car.getTotalPrice() + " VND");
    }
}