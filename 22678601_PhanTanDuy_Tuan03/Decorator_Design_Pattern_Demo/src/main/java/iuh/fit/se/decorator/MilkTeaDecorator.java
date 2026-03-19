package iuh.fit.se.decorator;

import iuh.fit.se.component.MilkTea;

public abstract class MilkTeaDecorator implements MilkTea {
    protected MilkTea decoratedTea;
    public MilkTeaDecorator(MilkTea tea) {
        this.decoratedTea = tea;
    }

    public String getDescription() {
        return decoratedTea.getDescription();
    }

    public double getCost() {
        return decoratedTea.getCost();
    }
}
