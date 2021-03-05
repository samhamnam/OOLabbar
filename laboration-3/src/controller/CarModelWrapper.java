package controller;

import cars.Transporter;
import controller.abracts.IEventListener;
import controller.abracts.IModel;
import util.Tuple;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarModelWrapper implements IModel,IEventListener<CarEvent> {
    private CarModel model;
    private ArrayList<CarEvent> events = new ArrayList<>();

    public CarModelWrapper(CarModel model){
        this.model = model;
    }

    public Tuple<Double,String> getFirstSpeedAndClass() {

        return model.getFirstSpeedAndClass();
    }

    public ArrayList<Tuple<String, Point2D>> getCarPositionsAndName() {
        return model.getCarPositionsAndName();
    }

    @Override
    public void update() {
        model = model.update(events);
    }

    @Override
    public void newEvent(CarEvent e) {
        events.add(e);
    }
}
