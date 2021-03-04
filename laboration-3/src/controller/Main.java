package controller;

import cars.*;
import controller.interfaces.Controller;
import controller.interfaces.IEventListener;
import controller.interfaces.IModel;
import controller.interfaces.IView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        new Main().program();
    }

    public void program(){
        ArrayList<Transporter> cars = new ArrayList<>();
        cars.add(new Volvo240());
        cars.add(new Saab95());
        cars.add(new Scania());

        CarModel model = new CarModel(cars);

        HashSet<IModel> models = new HashSet<>();
        models.add(model);
        HashSet<IEventListener<CarEvent>> listeners = new HashSet<>();
        listeners.add(model);

        ButtonView buttonView = new ButtonView(0,500,800,300, Color.red, new GridLayout(2,2));

        CarController carController = new CarController(buttonView,listeners);

        HashSet<Controller<CarEvent>> controllers = new HashSet<>();
        controllers.add(carController);

        HashSet<IView<JComponent>> views = new HashSet<>();
        views.add(new CarSpeedView(model));
        views.add(buttonView);
        views.add(new CarView(model));

        MainModel<JComponent> app = new MainModel<>(
                models,
                views,
                new CarWindow("Car-Sim", 800, 800)
        );
        //MainMethodFactory.createMainModel().run();
        app.run();
    }
}
