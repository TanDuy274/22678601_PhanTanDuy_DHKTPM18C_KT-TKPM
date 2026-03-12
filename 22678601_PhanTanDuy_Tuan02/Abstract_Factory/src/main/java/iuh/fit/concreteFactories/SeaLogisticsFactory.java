package iuh.fit.concreteFactories;

import iuh.fit.concreteProducts.Sailors;
import iuh.fit.concreteProducts.Ship;
import iuh.fit.factoryInterface.LogisticsFactory;
import iuh.fit.interfaces.Crew;
import iuh.fit.interfaces.Transport;

public class SeaLogisticsFactory implements LogisticsFactory {
    public Transport createTransport() {
        return new Ship();
    }
    public Crew createCrew() {
        return new Sailors();
    }
}
