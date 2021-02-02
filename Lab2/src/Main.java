import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) {
        Workshop<Car> workshop = new Workshop<>(10);
        workshop.addCar(new Scania());
        workshop.getCar(0);
    }
}
