package iuh.fit.se.context;

import iuh.fit.se.interfaces.TaxStrategy;

public class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy;

    public Product(String name, double price, TaxStrategy strategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = strategy;
    }

    public double getTotalPrice() {
        return price + taxStrategy.calculate(price);
    }
}
