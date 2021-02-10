import java.awt.*;

/**
 * A truck with a pickup that can be lowered and raised
 */
  abstract class Truck extends Car {
    private double truckBedAngle = 0; // Current angle of the truckbed.

    /**
     * Returns the max angle for the truckbed.
     * @return int
     */
    abstract int getMaxAngle();
    /**
     * Returns the minimum angle for the truckbed.
     * @return int
     */
    abstract int getMinAngle();
    /**
     * Returns how much the truck bed's angle is changed on calling raisePickup() and lowerPickup().
     * @return double
     */
    abstract double getPickUpIncrement();

    /**
     * Returns the truckbed angle.
     * @return double
     */
    public double getTruckbedAngle(){
         return truckBedAngle;
     }

    /**
     * Creates a truck with the following parameters:
     * @param nDoors the amount of doors a truck should have.
     * @param color the color of the truck.
     * @param enginePower how strong the truck engine is.
     * @param name the name of the truck.
     */
    public Truck(int nDoors, Color color, int enginePower, String name) {
        super(nDoors,color,enginePower,name);
    }

    /**
     * Creates a truck with the following parameters:
     * @param nDoors the amount of doors a truck should have.
     * @param color the color of the truck.
     * @param enginePower how strong the truck engine is.
     * @param name the name of the truck.
     * @param nav The navigation instance.
     */
    public Truck(int nDoors, Color color, int enginePower, String name, Navigation nav) {
        super(nDoors,color,enginePower,name, nav);
    }

    /**
     * Starts the engine.
     */
    public void startEngine(){
        if(getTruckbedAngle() == getMinAngle())
            super.startEngine();
    }


    /**
     * Raises the truckbed angle.
     */
    public void raisePickup(){
        if (getCurrentSpeed() == 0) {
            double pickUpIncrement = getPickUpIncrement();
            double minAngle = getMinAngle();
            double maxAngle = getMaxAngle();
            truckBedAngle = clamp(truckBedAngle + pickUpIncrement, minAngle, maxAngle);
        }
    }
    /**
     * Lowers the truckbed angle.
     */
    public void lowerPickup(){
        if (getCurrentSpeed() == 0) {
            double pickUpIncrement = getPickUpIncrement();
            double minAngle = getMinAngle();
            double maxAngle = getMaxAngle();
            truckBedAngle = clamp(truckBedAngle - pickUpIncrement, minAngle, maxAngle);
        }
    }
}
