package iuh.fit.concrete;

import iuh.fit.interfaces.Transport;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Giao hàng bằng đường bộ bằng Xe tải.");
    }
}
