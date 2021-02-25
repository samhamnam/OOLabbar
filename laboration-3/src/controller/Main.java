package controller;

import cars.*;
import com.sun.jdi.ArrayReference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        new Main().program();
    }

    public void program(){
        ArrayList<Transporter> cars = new ArrayList<>();
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());

        ArrayList<IView<JComponent>> carViews = new ArrayList<>();
        ArrayList<IController<Integer, JComponent>> carControllers = new ArrayList<>();
        ArrayList<IModel<Integer, JComponent>> models = new ArrayList<>();
        models.add(new CarModel<>(carControllers, carViews, cars));

        Application<Integer, JComponent> app = new Application<>(
                models,
                new CarWindow<>("Car-Sim", 800, 800)
        );
        app.run();
    }
}
