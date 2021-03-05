package controller;

import cars.Saab95;
import cars.Transporter;
import cars.Truck;
import cars.Volvo240;
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

    public void gas(double amount) {
        model.gas(amount);
    }

    public void brake(double amount) {
        model.brake(amount);
    }

    public void turboOn(double amount) {
        model.turboOn(amount);
    }

    public void turboOff(double amount) {
        model.turboOff(amount);
    }

    public void pickupUp(double amount) {
        model.pickupUp(amount);
    }

    public void pickupDown(double amount) {
        model.pickupDown(amount);
    }

    public void setSpeed(double amount) {
        model.setSpeed(amount);
    }

    public void addCar(double amount){
        model.addCar(amount);
    }

    public void removeCar(double amount){
        model.removeCar(amount);
    }
}
