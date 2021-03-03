package controller;

import cars.Car;
import cars.Saab95;
import cars.Transporter;
import cars.Truck;
import controller.CarEvent.Command;
import util.Threple;
import util.Tuple;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class CarModel implements IModel<CarEvent, JComponent> {
    private final ArrayList<Threple<Transporter, JLabel, JLabel>> cars;
    private final ArrayList<IController<CarEvent, JComponent>> controllers;
    private EventMatching<Command> EM;

    private double speed = 0;

    private final IView<JComponent> carView;
    private final IView<JComponent> speedLabelView;

    public CarModel(ArrayList<IController<CarEvent, JComponent>> controllers, IView<JComponent> carView, IView<JComponent> speedLabelView, ArrayList<Threple<Transporter,JLabel, JLabel>> cars) {
        this.carView = (carView);
        this.speedLabelView = (speedLabelView);

        this.controllers = controllers;
        this.cars = cars;

        speedLabelView.addPaintables(getCarLabels());
        carView.addPaintables(getCarPictures());

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
        ArrayList<IView<JComponent>> views = new ArrayList<>();
        views.add(speedLabelView);
        views.add(carView);
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

        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            JLabel pic = t.getRight();
            JLabel speedLabel = t.getMiddle();
            car.move();

            int x = (int) Math.round(car.nav.getPosition().getX());
            int y = (int) Math.round(car.nav.getPosition().getY());

            if (x < 0 || x > 690 || y < 0 || y > 700) {
                car.nav.setPosition(new Point2D.Double(Car.clamp(x,0,690),Car.clamp(y,0,700)));
                car.turnLeft();
                car.turnLeft();
            }

            speedLabel.setText(car.getClass().getSimpleName() + ": " + car.getCurrentSpeed());

            pic.setBounds(
                x,
                y,
                100, 60
            );
        }
    }

    public ArrayList<JComponent> getCarLabels(){
        ArrayList<JComponent> labels = new ArrayList<>();
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            labels.add(t.getMiddle());
        }
        return labels;
    }

    public ArrayList<JComponent> getCarPictures() {
        ArrayList<JComponent> carPictures = new ArrayList<>();
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            carPictures.add(t.getRight());
        }
        return carPictures;
    }


    void gas(double amount) {
        double gas = ((double) speed) / 100;
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.gas(gas);
        }
    }

    void brake(double amount) {
        double gas = ((double) amount) / 100;
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            car.brake(gas);
        }
    }


    void turboOn(double amount) {
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff(double amount) {
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void pickupUp(double amount) {
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
            Transporter car = t.getLeft();
            if (car instanceof Truck) {
                ((Truck) car).raisePickup();
            }
        }
    }

    void pickupDown(double amount) {
        for (Threple<Transporter,JLabel, JLabel> t : cars) {
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
