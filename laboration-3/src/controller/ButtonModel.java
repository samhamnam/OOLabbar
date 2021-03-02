package controller;

import cars.Car;
import cars.Transporter;
import util.Tuple;

import javax.swing.*;
import java.util.ArrayList;


public class ButtonModel implements IModel<CarEvent, JComponent> {
    private final ArrayList<IController<CarEvent, JComponent>> controllers;
    private final ArrayList<IView<JComponent>> views;

    public ButtonModel(ArrayList<IController<CarEvent, JComponent>> controllers, ArrayList<IView<JComponent>>views) {
        this.controllers = controllers;
        this.views = views;

        for (IView<JComponent> v : views) {
            for (IController<CarEvent, JComponent> c : controllers) {
                v.addPaintables(c.getPaintables());
            }
        }
    }

    @Override
    public ArrayList<IController<CarEvent,JComponent>> getControllers() {
        return controllers;
    }

    @Override
    public ArrayList<IView<JComponent>> getViews() {
        return views;
    }

    @Override
    public void update() {

    }
}
