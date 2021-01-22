package src;

import java.awt.*;


/**
 * @author      Lukas Gartman, Samul Hammersberg, Kristoffer Gustafsson.
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
    protected double[] position; // The current position of the car.

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
        this.position  = new double[]{0,0};
        stopEngine();
    }

    public Car(int nrDoors, Color color, int enginePower, String modelName, Dir direction, double[] position) {
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
    public Color getColor(){
        return color;
    }
    public void setColor(Color clr){
        color = clr;
    }
    public Dir getDirection() {
        return direction;
    }
    public double[] getPosition() {
        return position;
    }


    /**
     * Speed up the car.
     * @param amount: change amount.
     * @return void
     */
    abstract public void gas(double amount);

    /**
     * Slow down the car.
     * @param amount: change amount.
     * @return void
     */
    abstract public void brake(double amount);

    /**
     * Starts the car.
     * @return void
     */
    abstract void startEngine();
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
            case UP: position[1] += speedFactor(); break;
            case DOWN: position[1] -= speedFactor(); break;
            case LEFT: position[0] -= speedFactor(); break;
            case RIGHT: position[0] += speedFactor(); break;
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
}
