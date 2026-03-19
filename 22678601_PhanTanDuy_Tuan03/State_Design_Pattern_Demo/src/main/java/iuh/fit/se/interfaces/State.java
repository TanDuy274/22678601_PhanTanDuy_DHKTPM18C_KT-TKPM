package iuh.fit.se.interfaces;

import iuh.fit.se.models.Fan;

public interface State {
    void handleRequest(Fan fan);
}
