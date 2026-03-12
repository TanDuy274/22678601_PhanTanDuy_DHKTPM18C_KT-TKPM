package iuh.fit.concreteFactories;

import iuh.fit.concreteProducts.Drivers;
import iuh.fit.concreteProducts.Truck;
import iuh.fit.factoryInterface.LogisticsFactory;
import iuh.fit.interfaces.Crew;
import iuh.fit.interfaces.Transport;

public class RoadLogisticsFactory implements LogisticsFactory {
    public Transport createTransport() {
        return new Truck();
    }

    public Crew createCrew() {
        return new Drivers();
    }
}
