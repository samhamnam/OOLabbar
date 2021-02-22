package controller;

import java.util.ArrayList;

public interface IController<Event, Paintable>  {
    void clearEvents();
    ArrayList<Event> getEvents();
    ArrayList<Paintable> getPaintables();
}
