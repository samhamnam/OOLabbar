package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarWindow implements IWindow<JComponent> {
    private final JFrame window = new JFrame();
    private final JPanel panel = new JPanel();

    public CarWindow(String title, int x, int y){
        panel.setLayout(null);

        window.setPreferredSize(new Dimension(x,y));
        panel.setPreferredSize(new Dimension(x,y));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setTitle(title);
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(panel);
        window.pack();
    }

    @Override
    public void repaint() {
        window.validate();
        window.repaint();
    }

    @Override
    public void add(JComponent p) {
        panel.add(p);
    }

    @Override
    public void remove(JComponent p) {
        panel.remove(p);
    }
}
