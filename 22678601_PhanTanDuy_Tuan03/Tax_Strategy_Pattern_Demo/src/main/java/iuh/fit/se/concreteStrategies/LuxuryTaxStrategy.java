package iuh.fit.se.concreteStrategies;

import iuh.fit.se.interfaces.TaxStrategy;

public class LuxuryTaxStrategy implements TaxStrategy {
    public double calculate(double price) {
        return price * 0.5;
    }
}
