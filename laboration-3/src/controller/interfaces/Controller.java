package controller.interfaces;

import java.util.HashSet;

public abstract class Controller<Event> {
    HashSet<IEventListener<Event>> listeners;

    public Controller(HashSet<IEventListener<Event>> listeners) {
        this.listeners = listeners;
    }

    public void notifyObservers(Event e) {
        for (IEventListener<Event> o : listeners) {
            o.newEvent(e);
        }
    }
}
