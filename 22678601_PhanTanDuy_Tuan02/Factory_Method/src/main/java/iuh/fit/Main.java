package iuh.fit;

import iuh.fit.concreteCreators.RoadLogistics;
import iuh.fit.creator.Logistics;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Logistics log = new RoadLogistics();
        log.planDelivery(); // Output: Giao hàng bằng đường bộ bằng Xe tải.
    }
}