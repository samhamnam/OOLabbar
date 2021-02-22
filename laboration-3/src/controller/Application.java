package controller;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Application<Event, Paintable> {
    public Application(IModel<Event, Paintable>[] models) {
        this.models = models;

        views = new HashSet<>();
        for(IModel<Event, Paintable> model : models) {
            views.add(model.getView());
        }
    }

    private final IModel<Event, Paintable>[] models;
    private final Set<IView<Paintable>> views;

    private boolean run = true;

    public void run() {


        run = true;
        while(run){
            update();
        }
    }

    private void update() {
        ArrayList<Paintable> paintables = new ArrayList<>();
        for(IModel<Event,Paintable> model : models) {
            ArrayList<Event> events = new ArrayList<>();
            for(IController<Event,Paintable> controller : model.getControllers()) {
                events.addAll(controller.getEvents());
                paintables.addAll(controller.getPaintables());
            }
            model.update(events);
            paintables.addAll(model.getPaintables());
            model.getView().addPaintables(paintables);
        }
    }

    public void quit() {
        run = false;
    }
}
