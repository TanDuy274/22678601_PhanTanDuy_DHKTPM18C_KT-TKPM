package iuh.fit.concreteCreators;

import iuh.fit.concrete.Truck;
import iuh.fit.creator.Logistics;
import iuh.fit.interfaces.Transport;

public class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}
