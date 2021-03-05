package controller.abracts;

public interface IEventListener<Event> {
    void newEvent(Event e);
}
