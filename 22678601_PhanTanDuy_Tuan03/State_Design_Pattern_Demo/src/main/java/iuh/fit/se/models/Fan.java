package iuh.fit.se.models;

import iuh.fit.se.concreteStates.OffState;
import iuh.fit.se.interfaces.State;

public class Fan {
    private State currentState;

    public Fan() { currentState = new OffState(); }
    public void setState(State state) { this.currentState = state; }
    public void pushButton() { currentState.handleRequest(this); }
}
