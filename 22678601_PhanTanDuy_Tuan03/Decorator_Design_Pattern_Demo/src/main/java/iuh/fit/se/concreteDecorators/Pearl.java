package iuh.fit.se.concreteDecorators;

import iuh.fit.se.component.MilkTea;
import iuh.fit.se.decorator.MilkTeaDecorator;

public class Pearl extends MilkTeaDecorator {
    public Pearl(MilkTea tea) {
        super(tea);
    }

    public String getDescription() {
        return super.getDescription() + " + Trân châu";
    }

    public double getCost() {
        return super.getCost() + 5.0;
    }
}
