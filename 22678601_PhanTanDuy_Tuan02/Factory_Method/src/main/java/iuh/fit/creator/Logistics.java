package iuh.fit.creator;

import iuh.fit.interfaces.Transport;

public abstract class Logistics {
    public void planDelivery() {
        Transport t = createTransport();
        t.deliver();
    }

    public abstract Transport createTransport();
}
