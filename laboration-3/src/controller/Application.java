package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Application<Event, Paintable> {
    private final ArrayList<IModel<Event, Paintable>> models;
    private final IWindow<Paintable> window;
    private final HashSet<IView<Paintable>> views;

    private final Timer timer = new Timer(100, new TimerListener());

    public Application(ArrayList<IModel<Event, Paintable>> models, IWindow<Paintable> window) {
        this.models = models;
        this.window = window;
        views = new HashSet<>();
        for(IModel<Event, Paintable> model : models) {
            views.addAll(model.getViews());
        }
        for(IView<Paintable> view : views) {
            window.add(view.getPanel());
        }

    }

    public void run() {
        timer.start();
    }

    private void update() {
        for(IModel<Event,Paintable> model : models) {
             model.update();
        }
        window.repaint();
    }

    public void quit() {
        timer.stop();
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            update();
        }
    }
}
