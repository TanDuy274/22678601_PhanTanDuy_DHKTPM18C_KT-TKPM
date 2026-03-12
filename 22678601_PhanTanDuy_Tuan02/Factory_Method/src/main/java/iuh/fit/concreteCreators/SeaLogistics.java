package iuh.fit.concreteCreators;

import iuh.fit.concrete.Ship;
import iuh.fit.creator.Logistics;
import iuh.fit.interfaces.Transport;

public class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
