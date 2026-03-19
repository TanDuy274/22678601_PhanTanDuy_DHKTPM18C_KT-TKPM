package iuh.fit.se.concreteStates;

import iuh.fit.se.interfaces.State;
import iuh.fit.se.models.Fan;

public class OffState implements State {
    public void handleRequest(Fan fan) {
        System.out.println("Quạt đang TẮT. Chuyển sang tốc độ THẤP.");
        fan.setState(new LowState());
    }
}
