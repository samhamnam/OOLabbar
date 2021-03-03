package controller;

import cars.*;
import com.sun.jdi.ArrayReference;
import util.PictureLoader;
import util.Threple;
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
        ArrayList<Threple<Transporter,JLabel,JLabel>> cars = new ArrayList<>();
        cars.add(new Threple<>(new Volvo240(),new JLabel(""), new JLabel(new ImageIcon(PictureLoader.getImage(new Volvo240())))));
        cars.add(new Threple<>(new Saab95(),new JLabel(""), new JLabel(new ImageIcon(PictureLoader.getImage(new Saab95())))));
        cars.add(new Threple<>(new Scania(),new JLabel(""), new JLabel(new ImageIcon(PictureLoader.getImage(new Scania())))));


        IView<JComponent> speedView = new CarView(0,600-128,128,128, Color.CYAN,new GridLayout(6,1)); // Speed
        IView<JComponent> carView =new CarView(0,0,800,600,Color.gray,null); // Cars
        ArrayList<IView<JComponent>> buttonViews = new ArrayList<>();
        buttonViews.add(new CarView(0,600,800,200, Color.green, new GridLayout(2,6))); // Buttons

        ArrayList<IController<CarEvent, JComponent>> carControllers = new ArrayList<>();
        carControllers.add(new CarController());
        ArrayList<IModel<CarEvent, JComponent>> models = new ArrayList<>();
        models.add(new CarModel(carControllers, carView,speedView, cars));
        models.add(new ButtonModel(carControllers,  buttonViews));

        Application<CarEvent, JComponent> app = new Application<>(
                models,
                new CarWindow("Car-Sim", 800, 800)
        );
        app.run();
    }
}
