package iuh.fit.client;

import iuh.fit.factoryInterface.LogisticsFactory;
import iuh.fit.interfaces.Crew;
import iuh.fit.interfaces.Transport;

public class LogisticsSystem {
    private Transport transport;
    private Crew crew;

    // Client nhận vào một Abstract Factory
    public LogisticsSystem(LogisticsFactory factory) {
        this.transport = factory.createTransport();
        this.crew = factory.createCrew();
    }

    public void startOperations() {
        transport.deliver();
        crew.manage();
    }
}
