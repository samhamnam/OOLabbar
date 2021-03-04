package controller;

import cars.Car;
import controller.interfaces.IView;
import util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CarView implements IView<JComponent> {
    ArrayList<Tuple<BufferedImage, Point2D>> cars;

    private final JPanel view = new JPanel(null) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(cars != null){
                for (Tuple<BufferedImage, Point2D> car : cars) {
                    int x = (int) Math.round(car.getRight().getX());
                    int y = (int) Math.round(car.getRight().getY());
                    g.drawImage(car.getLeft(), x, y, null);
                }
            }
        }
    };
    private CarModel model;

    public CarView (CarModel model){
        view.setBounds(0,0,800,500);
        view.setBackground(Color.orange);
        this.model = model;
    }

    @Override
    public JComponent getPanel() {
        return view;
    }

    @Override
    public void update() {
        cars = model.getCarPositionsAndImage();
    }
}
