package iuh.fit.se.concreteStates;

import iuh.fit.se.interfaces.State;
import iuh.fit.se.models.Fan;

public class HighState implements State {
    public void handleRequest(Fan fan) {
        System.out.println("Quạt đang chạy CAO. Chuyển sang TẮT.");
        fan.setState(new OffState());
    }
}
