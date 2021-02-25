package controller;

import cars.Transporter;
import util.PictureLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

public class CarModel<Event extends Integer, Paintable extends JComponent> implements IModel<Event, Paintable> {
    private final ArrayList<Transporter> cars;
    private final ArrayList<IController<Event,Paintable>> controllers;
    private final ArrayList<IView<Paintable>> views;

    public CarModel(ArrayList<IController<Event,Paintable>> controllers, ArrayList<IView<Paintable>>views, ArrayList<Transporter>cars) {
        this.controllers = controllers;
        this.views = views;
        this.cars = cars;

        CarView<Paintable> cv = new CarView<>();
        cv.addPaintables(getPaintables());
        views.add(cv);
    }

    public ArrayList<IController<Event,Paintable>> getControllers(){
        return controllers;
    }

    @Override
    public ArrayList<IView<Paintable>> getViews() {
        return views;
    }

    public void update(ArrayList<Event> arrayList) {
        for (Transporter t : cars){
            t.move();
        }
    }

    public ArrayList<Paintable> getPaintables() {
        ArrayList<Paintable> pictures = new ArrayList<>();
        for(Transporter t : cars){
            pictures.add((Paintable) new JLabel(new ImageIcon(PictureLoader.getImage(t))));
        }
        return pictures;
    }
}
