package controller;

import java.awt.*;
import java.util.ArrayList;

public interface IModel<Event, Paintable>{
    IController<Event, Paintable>[] getControllers();
    IView<Paintable>[] getViews();
    void update(ArrayList<Event> events);
    ArrayList<Paintable> getPaintables();
}
