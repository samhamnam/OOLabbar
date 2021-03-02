package controller;

import cars.Car;
import cars.Saab95;
import cars.Transporter;
import cars.Truck;
import controller.CarEvent.Command;
import util.Tuple;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarModel implements IModel<CarEvent, JComponent> {
    private final ArrayList<Tuple<Transporter, JLabel>> cars;
    private final ArrayList<IController<CarEvent, JComponent>> controllers;
    private final ArrayList<IView<JComponent>> views;
    private EventMatching<Command> EM;

    private double speed = 0;

    public CarModel(ArrayList<IController<CarEvent, JComponent>> controllers, ArrayList<IView<JComponent>> views, ArrayList<Tuple<Transporter, JLabel>> cars) {
        this.controllers = controllers;
        this.views = views;
        this.cars = cars;

        views.get(0).addPaintables(getCarPictures());


        assign();
    }

    private void assign() {
        this.EM = new EventMatching<>(Command.values());
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
        EM.assign(
                Command.GAS_SPEED,
                this::setSpeed
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
        ArrayList<CarEvent> events = new ArrayList<>();
        for(IController<CarEvent,JComponent> controller: controllers) {
            events.addAll(controller.getEvents());
            controller.clearEvents();
        }
        for (CarEvent event : events) {
            EM.getFunc(event.getCommand()).apply(event.getAmount());
        }

        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            JLabel pic = t.getRight();
            car.move();

            int x = (int) Math.round(car.nav.getPosition().getX());
            int y = (int) Math.round(car.nav.getPosition().getY());

            if (x < 0 || x > 690 || y < 0 || y > 700) {
                car.nav.setPosition(new Point2D.Double(Car.clamp(x,0,690),Car.clamp(y,0,700)));
                car.turnLeft();
                car.turnLeft();
            }

            pic.setBounds(
                x,
                y,
                100, 60
            );
        }
    }

    public ArrayList<JComponent> getCarPictures() {
        ArrayList<JComponent> carPictures = new ArrayList<>();
        for (Tuple<Transporter, JLabel> t : cars) {
            carPictures.add(t.getRight());
        }
        return carPictures;
    }


    void gas(double amount) {
        double gas = ((double) speed) / 100;
        for (Tuple<Transporter, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.gas(gas);
        }
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
    void setSpeed(double amount) {
        speed = amount;
        System.out.println(amount);
    }
}
