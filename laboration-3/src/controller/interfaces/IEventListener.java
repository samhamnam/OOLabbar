package controller.interfaces;

public interface IEventListener<Event> {
    void newEvent(Event e);
}
