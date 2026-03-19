package iuh.fit.se.concreteStrategies;

import iuh.fit.se.interfaces.TaxStrategy;

public class VATStrategy implements TaxStrategy {
    public double calculate(double price) {
        return price * 0.1;
    }
}
