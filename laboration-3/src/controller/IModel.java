package controller;

import java.awt.*;
import java.util.ArrayList;

public interface IModel<Event, Paintable>{
    ArrayList<IController<Event, Paintable>> getControllers();
    ArrayList<IView<Paintable>> getViews();
    void update(ArrayList<Event> events);
    ArrayList<Paintable> getPaintables();
}
