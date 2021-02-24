package controller;

import cars.Transporter;

import javax.swing.*;
import java.util.ArrayList;

public class CarView<T extends Transporter> extends JPanel implements IView<T> {
    @Override
    public void addPaintables(ArrayList arrayList) {

    }

    @Override
    public T getPanel() {
        return null;
    }

    @Override
    public void paint() {

    }
}
