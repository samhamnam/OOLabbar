package controller;

import java.awt.*;
import java.util.ArrayList;

public interface IModel<Event, Paintable>{
    IController<Event, Paintable>[] getControllers();
    IView<Paintable> getView();
    void update(ArrayList<Event> events);
    ArrayList<Paintable> getPaintables();
}
