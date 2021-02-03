import org.junit.Ignore;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * @author      Lukas Gartman, Samuel Hammersberg, Kristoffer Gustafsson.
 * @version     1.0
 * @since       1.0
 */
public abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car

    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name
    private Dir direction; // The current direction the car is going in.
    private Point2D.Double position; // The current position of the car.

    public enum Dir {
        LEFT,
        UP,
        RIGHT,
        DOWN
    }

    public Car(int nrDoors, Color color, int enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.direction = Dir.RIGHT;
        this.position  = new Point2D.Double(0,0);
        stopEngine();
    }

    public Car(int nrDoors, Color color, int enginePower, String modelName, Dir direction, Point2D.Double position) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.direction = direction;
        this.position = position;
        stopEngine();
    }
    /**
     * Returns the number of doors
     * @return int
     */
    public int getNrDoors(){
        return nrDoors;
    }
    /**
     * Returns the power of the engine
     * @return double
     */
    public double getEnginePower(){
        return enginePower;
    }
    /**
     * Returns the speed of the car
     * @return double
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Sets the speed of the car
     * @param amount: amount to set currentSpeed to.
     */
    public void setCurrentSpeed(double amount) {
        this.currentSpeed = clamp(amount,0,enginePower);
    }
    /**
     * returns the color of the car
     * @return Color
     */
    public Color getColor(){
        return color;
    }
    /**
     * Sets the color of the car
     * @param clr The colo of the car
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Gets the position of the car.
     * @return Point2D.Double
     */
    public Point2D.Double getPosition() {
        return position;
    }
    /**
     * Returns the direction.
     * @return Dir
     */
    public Dir getDirection() {return direction;}
    /**
     * Returns the name of the model.
     * @return String
     */
    public String getModelName() {
        return modelName;
    }
    /**
     * Sets the numbers of doors of the car.
     * @param nrDoors number of doors to set to.
     */
    public void setNrDoors(int nrDoors) {
        this.nrDoors = nrDoors;
    }
    /**
     * Sets the power of the engine.
     * @param enginePower the amount to set the the power of the engine to.
     */
    public void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }
    /**
     * Sets the car's model name
     * @param modelName the new name of which to set the model name to.
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * Sets the direction of the car
     * @param direction the new direction of the car
     */
    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    /**
     * Sets the position of the car
     * @param position the new position of the car
     */
    public void setPosition(Point2D.Double position) {
        this.position = position;
    }

    /**
     * Starts the car.
     */
    public void startEngine() {
        currentSpeed = 0.1;
    }
    /**
     * Stops the car.
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * How speed is calculated.
     */
    abstract double speedFactor();

    /**
     * Move the car in the current direction.
     */
    public void move() {
        double radDir = getDirectionToRad(direction);
        position.x = Math.cos(radDir);
        position.y = Math.sin(radDir);
    }

    private double getDirectionToRad(Dir dir){
        double d = dir.ordinal() / (Dir.values().length - 1.0);
        return d * 2 * Math.PI;
    }

    /**
     * Turns the car left.
     */
    public void turnLeft() {
        int dir = direction.ordinal();
        dir -= 1;
        if(dir < 0) dir = Dir.values().length - 1;
        direction = Dir.values()[dir];
    }

    /**
     * Turns the car right.
     */
    public void turnRight() {
        int dir = direction.ordinal();
        dir += 1;
        if(dir > Dir.values().length - 1) dir = 0;
        direction = Dir.values()[dir];
    }

    /**
     * Increment the speed.
     * @param amount: amount to increment by.
     */
    public void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    /**
     * Decrement the speed.
     * @param amount: amount to decrement by.
     */
    public void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    /**
     * Speed up the car.
     * @param amount: change amount.
     */
    public void gas(double amount) {
        amount = clamp(amount, 0,1);
        incrementSpeed(amount);
    }

    /**
     * Speed down the car.
     * @param amount: change amount.
     */
    public void brake(double amount) {
        amount = clamp(amount, 0, 1);
        decrementSpeed(amount);
    }

    /**
     * Clamps a value between two points.
     * @param x: value to be clamped.
     * @param min: minimum clamp value.
     * @param max: maximum clamp value.
     * @return double
     */
    @Ignore
    double clamp(double x, double min, double max) {
        if (x > max)
            x = max;
        else if (x < min)
            x = min;
        return x;
    }
}
