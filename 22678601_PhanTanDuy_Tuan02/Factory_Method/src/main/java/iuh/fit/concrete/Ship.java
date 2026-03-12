package iuh.fit.concrete;

import iuh.fit.interfaces.Transport;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Giao hàng bằng đường biển bằng Tàu thủy.");
    }
}
