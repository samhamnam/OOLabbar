package controller;

import cars.*;
import com.sun.jdi.ArrayReference;
import util.PictureLoader;
import util.Tuple;

import javax.management.remote.JMXConnectorServerFactory;
import javax.swing.*;
import javax.swing.text.View;
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
        ArrayList<Tuple<Transporter,JLabel>> cars = new ArrayList<>();
        cars.add(new Tuple<>(new Volvo240(), new JLabel(new ImageIcon(PictureLoader.getImage(new Volvo240())))));
        cars.add(new Tuple<>(new Saab95(), new JLabel(new ImageIcon(PictureLoader.getImage(new Saab95())))));
        cars.add(new Tuple<>(new Scania(), new JLabel(new ImageIcon(PictureLoader.getImage(new Scania())))));

        ArrayList<IView<JComponent>> carViews = new ArrayList<>();
        ArrayList<IView<JComponent>> buttonViews = new ArrayList<>();

        ArrayList<IController<CarEvent, JComponent>> carControllers = new ArrayList<>();
        carControllers.add(new CarController());
        ArrayList<IModel<CarEvent, JComponent>> models = new ArrayList<>();
        models.add(new CarModel(carControllers, carViews, cars));
        models.add(new ButtonModel(carControllers,  buttonViews));

        Application<CarEvent, JComponent> app = new Application<>(
                models,
                new CarWindow("Car-Sim", 800, 800)
        );
        app.run();
    }
}
