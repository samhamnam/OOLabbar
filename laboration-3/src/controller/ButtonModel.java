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

        ButtonView bv = new ButtonView();
        //CarController cc = new CarController();
        for(IController<CarEvent, JComponent> controller : controllers) {
            bv.addPaintables(controller.getPaintables());
            System.out.println("YTYO");
        }
        views.add(bv);
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

    @Override
    public ArrayList<JComponent> getPaintables() {
        return null;
    }
}
