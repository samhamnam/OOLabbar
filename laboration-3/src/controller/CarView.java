package controller;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CarView implements IView<JComponent> {
    private final JPanel panel;

    public CarView(int x, int y, int width, int height, Color color, LayoutManager layout) {
        panel = new JPanel(layout);
        panel.setBounds(x,y,width,height);
        panel.setBackground(color);
    }

    @Override
    public void addPaintables(ArrayList<JComponent> paintables) {
        for (JComponent p : paintables) {
            panel.add(p);
        }
        panel.repaint();
        panel.revalidate();
    }

    @Override
    public JComponent getPanel() {
        return panel;
    }
}
