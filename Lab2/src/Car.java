import java.awt.*;
import java.awt.geom.Point2D;


/**
 * @author      Lukas Gartman, Samuel Hammersberg, Kristoffer Gustafsson.
 * @version     1.0
 * @since       1.0
 */
public abstract class Car extends Transporter {
    private int nrDoors; // Number of doors on the c
    private double enginePower; // Engine power of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    /**
     * Creates a Car.
     * @param nrDoors Amount of doors.
     * @param color Color of the car.
     * @param enginePower How strong the engine is.
     * @param modelName Name of the car model.
     */
    Car(int nrDoors, Color color, int enginePower, String modelName) {
        super(new Dir4Navigation());
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    /**
     * Creates a Car.
     * @param nrDoors Number of doors.
     * @param color Color of the car.
     * @param enginePower How strong the engine is.
     * @param modelName Name of the car model.
     * @param nav Navigation instance.
     */
    Car(int nrDoors, Color color, int enginePower, String modelName, Navigation nav) {
        super(nav);
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    /**
     * Returns the number of doors
     * @return int
     */
     int getNrDoors(){
        return nrDoors;
    }
    /**
     * Returns the power of the engine
     * @return double
     */
     double getEnginePower(){
        return enginePower;
    }
    /**
     * returns the color of the car
     * @return Color
     */
     Color getColor(){
        return color;
    }

    /**
     * Sets the color of the car
     * @param clr The colo of the car
     */
     void setColor(Color clr){
        color = clr;
    }

    /**
     * Returns the name of the model.
     * @return String
     */
     String getModelName() {
        return modelName;
    }
    /**
     * Sets the numbers of doors of the car.
     * @param nrDoors number of doors to set to.
     */
     void setNrDoors(int nrDoors) {
        this.nrDoors = nrDoors;
    }
    /**
     * Sets the power of the engine.
     * @param enginePower the amount to set the the power of the engine to.
     */
     void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }
    /**
     * Sets the car's model name
     * @param modelName the new name of which to set the model name to.
     */
     void setModelName(String modelName) {
        this.modelName = modelName;
    }
    /**
     * Starts the car.
     */
     void startEngine() {
        setCurrentSpeed(0.1);
    }
    /**
     * Stops the car.
     */
     void stopEngine(){
        setCurrentSpeed(0);
    }

    /**
     * Sets the speed of the car
     * @param amount: amount to set currentSpeed to.
     */
    void setCurrentSpeed(double amount) {
        super.setCurrentSpeed(Transporter.clamp(amount,0,enginePower));
    }
}
