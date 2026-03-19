package iuh.fit.se.concreteStates;

import iuh.fit.se.interfaces.State;
import iuh.fit.se.models.Fan;

public class LowState implements State {
    public void handleRequest(Fan fan) {
        System.out.println("Quạt đang chạy THẤP. Chuyển sang tốc độ CAO.");
        fan.setState(new HighState());
    }
}
