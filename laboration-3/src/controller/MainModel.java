package controller;

import controller.abracts.IModel;
import controller.abracts.IView;
import controller.abracts.IWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class MainModel<Paintable> {
    private final HashSet<IModel> models;
    private final IWindow<Paintable> window;
    private final HashSet<IView<Paintable>> views;

    private final Timer timer = new Timer(30, new TimerListener());

    public MainModel(HashSet<IModel> models, HashSet<IView<Paintable>> views, IWindow<Paintable> window) {
        this.models = models;
        this.window = window;
        this.views = views;
    }

    public void run() {
        timer.start();
    }

    private void update() {
        for (IModel model : models) {
            model.update();
        }
        for (IView<Paintable> view : views) {
            view.update();
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
