package iuh.fit;

import iuh.fit.client.LogisticsSystem;
import iuh.fit.concreteFactories.RoadLogisticsFactory;
import iuh.fit.concreteFactories.SeaLogisticsFactory;
import iuh.fit.factoryInterface.LogisticsFactory;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Giả sử cấu hình hệ thống chọn đường bộ
        LogisticsFactory roadFactory = new RoadLogisticsFactory();
        LogisticsSystem app = new LogisticsSystem(roadFactory);
        app.startOperations();

        System.out.println("---");

        // Giả sử cấu hình hệ thống đổi sang đường biển
        LogisticsFactory seaFactory = new SeaLogisticsFactory();
        LogisticsSystem appSea = new LogisticsSystem(seaFactory);
        appSea.startOperations();
    }
}