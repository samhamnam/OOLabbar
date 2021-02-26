package controller;

import cars.Saab95;
import cars.Transporter;
import cars.Truck;
import controller.CarEvent.Command;
import util.Tuple;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CarModel implements IModel<CarEvent, JComponent> {
    private final ArrayList<Tuple<Transporter, JLabel>> cars;
    private final ArrayList<IController<CarEvent, JComponent>> controllers;
    private final ArrayList<IView<JComponent>> views;
    private EventMatching<Command> EM;

    public CarModel(ArrayList<IController<CarEvent, JComponent>> controllers, ArrayList<IView<JComponent>> views, ArrayList<Tuple<Transporter, JLabel>> cars) {
        this.controllers = controllers;
        this.views = views;
        this.cars = cars;

        CarView cv = new CarView();
        cv.addPaintables(getPaintables());
        views.add(cv);
        assign();
    }

    private void assign() {
        this.EM = new EventMatching<Command>(Command.values());
        EM.assign(
                Command.GAS,
                this::gas
        );
        EM.assign(
                Command.BRAKE,
                this::brake
        );
        EM.assign(
                Command.LIFT_BED,
                this::pickupUp
        );
        EM.assign(
                Command.LOWER_BED,
                this::pickupDown
        );
        EM.assign(
                Command.TURBO_OFF,
                this::turboOff
        );
        EM.assign(
                Command.TURBO_ON,
                this::turboOn
        );
    }

    public ArrayList<IController<CarEvent, JComponent>> getControllers() {
        return controllers;
    }

    @Override
    public ArrayList<IView<JComponent>> getViews() {
        return views;
    }

    public void update() {

        ArrayList<CarEvent> evensts = new ArrayList<>();
        for(IController controller: controllers){
            evensts.addAll(controller.getEvents());
        }
        for (CarEvent event : evensts) {
            EM.getFunc(event.getCommand()).apply(event.getAmount());
        }
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.move();
            t.getRight().setBounds(
                    (int) car.nav.getPosition().x,
                    (int) car.nav.getPosition().y,
                    100, 60
            );
        }

    }

    public ArrayList<JComponent> getPaintables() {
        ArrayList<JComponent> carPictures = new ArrayList<>();
        for (Tuple<Transporter, JLabel> t : cars) {
            carPictures.add(t.getRight());
        }
        return carPictures;
    }


    void gas(double amount) {
        double gas = ((double) amount) / 100;
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.gas(gas);
        }
        System.out.println("GAS");
    }

    void brake(double amount) {
        double gas = ((double) amount) / 100;
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.brake(gas);
        }
    }


    void turboOn(double amount) {
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff(double amount) {
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void pickupUp(double amount) {
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Truck) {
                ((Truck) car).raisePickup();
            }
        }
    }

    void pickupDown(double amount) {
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Truck) {
                ((Truck) car).lowerPickup();
            }
        }
    }


}
