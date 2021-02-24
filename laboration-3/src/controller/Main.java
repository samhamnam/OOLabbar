package controller;

import cars.Car;
import controller.old.CarController;

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

    private final Application<Integer, JComponent> app = new Application<>(
            new IModel[]{},
            new CarWindow<>("Car-Sim",800,800)
    );

    public void program(){
        app.run();
    }
}
