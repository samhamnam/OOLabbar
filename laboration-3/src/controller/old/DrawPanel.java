package controller.old;

import cars.Car;
import util.Tuple;
import java.awt.image.BufferedImage;
import util.PictureLoader;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    // Just a single image, TODO: Generalize


    // To keep track of a singel cars position
    private Tuple<Car,BufferedImage>[] cars;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> carsIn) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.pink);

        cars = new Tuple[carsIn.size()];
        for (int i = 0; i < carsIn.size(); i++) {
            this.cars[i] = new Tuple<>(carsIn.get(i),PictureLoader.getImage(carsIn.get(i)));
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Tuple<Car, BufferedImage> car : cars) {
            int x = (int) Math.round(car.getLeft().nav.getPosition().x);
            int y = (int) Math.round(car.getLeft().nav.getPosition().y);
            g.drawImage(car.getRight(), x, y, null);
        }
    }
}
