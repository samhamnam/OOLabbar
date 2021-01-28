package src;

import org.junit.Ignore;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * @author      Lukas Gartman, Samuel Hammersberg, Kristoffer Gustafsson.
 * @version     1.0
 * @since       1.0
 */
public abstract class Car implements Movable {
    protected int nrDoors; // Number of doors on the car
    protected double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    protected Color color; // Color of the car
    protected String modelName; // The car model name
    protected Dir direction; // The current direction the car is going in.
    protected Point2D.Double position; // The current position of the car.

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

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }
    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public void setCurrentSpeed(double amount) {
        this.currentSpeed = clamp(amount,0,enginePower);
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }
    public Point2D.Double getPosition() {
        return position;
    }
    public Dir getDirection() {return direction;}


    /**
     * Starts the car.
     * @return void
     */
    public void startEngine() {
        currentSpeed = 0.1;
    }
    /**
     * Stops the car.
     * @return void
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * How speed is calculated.
     * @return void
     */
    abstract double speedFactor();

    /**
     * Move the car in the current direction.
     * @return void
     */
    public void move() {
        switch(direction) {
            case UP: position.y += speedFactor(); break;
            case DOWN: position.y -= speedFactor(); break;
            case LEFT: position.x -= speedFactor(); break;
            case RIGHT: position.x += speedFactor(); break;
        }
    }

    /**
     * Turns the car left.
     * @return void
     */
    public void turnLeft() {
        int dir = direction.ordinal();
        dir -= 1;
        if(dir < 0) dir = 3;
        direction = Dir.values()[dir];
    }

    /**
     * Turns the car right.
     * @return void
     */
    public void turnRight() {
        int dir = direction.ordinal();
        dir += 1;
        if(dir > 3) dir = 0;
        direction = Dir.values()[dir];
    }

    /**
     * Increment the speed.
     * @param amount: amount to increment by.
     * @return void
     */
    public void incrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() + speedFactor() * amount);
    }

    /**
     * Decrement the speed.
     * @param amount: amount to decrement by.
     * @return void
     */
    public void decrementSpeed(double amount) {
        setCurrentSpeed(getCurrentSpeed() - speedFactor() * amount);
    }

    /**
     * Speed up the car.
     * @param amount: change amount.
     * @return void
     */
    public void gas(double amount) {
        amount = clamp(amount, 0,1);
        incrementSpeed(amount);
    }

    /**
     * Speed down the car.
     * @param amount: change amount.
     * @return void
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
    private double clamp(double x, double min, double max) {
        if (x > max)
            x = max;
        else if (x < min)
            x = min;
        return x;
    }
}
