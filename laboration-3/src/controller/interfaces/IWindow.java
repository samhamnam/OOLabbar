package controller.interfaces;

public interface IWindow<Paintable> {
    void repaint();
    void add(Paintable p);
    void remove(Paintable p);
}
