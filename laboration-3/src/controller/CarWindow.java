package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarWindow <Paintable extends JComponent>  extends JFrame implements IWindow<Paintable> {
    private final JFrame window = new JFrame();

    public CarWindow(String title, int x, int y){
        window.setPreferredSize(new Dimension(x,y));

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setTitle(title);
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.pack();
    }

    @Override
    public void repaint() {
        super.validate();
        super.repaint();
    }

    @Override
    public void add(Paintable p) {
        super.add(p);
    }

    @Override
    public void remove(Paintable p) {
        super.remove(p);
    }
}
