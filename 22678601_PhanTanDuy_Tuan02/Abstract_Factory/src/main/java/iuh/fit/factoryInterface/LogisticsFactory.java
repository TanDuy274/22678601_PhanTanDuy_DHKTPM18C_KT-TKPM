package iuh.fit.factoryInterface;

import iuh.fit.interfaces.Crew;
import iuh.fit.interfaces.Transport;

public interface LogisticsFactory {
    Transport createTransport();
    Crew createCrew();
}
