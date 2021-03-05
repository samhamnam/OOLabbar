package controller.abracts;

public interface IView<Paintable> {
    Paintable getPanel();
    void update();
}
