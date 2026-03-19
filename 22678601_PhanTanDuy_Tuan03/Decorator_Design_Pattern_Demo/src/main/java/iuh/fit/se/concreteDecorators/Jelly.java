package iuh.fit.se.concreteDecorators;

import iuh.fit.se.component.MilkTea;
import iuh.fit.se.decorator.MilkTeaDecorator;

public class Jelly extends MilkTeaDecorator {
    public Jelly(MilkTea tea) {
        super(tea);
    }

    public String getDescription() {
        return super.getDescription() + " + Thạch";
    }

    public double getCost() {
        return super.getCost() + 7.0;
    }
}
