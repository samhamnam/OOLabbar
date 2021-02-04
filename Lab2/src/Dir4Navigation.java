public class Dir4Navigation extends Navigation {
    private Dir dir4; // The direction enum of the car.
    /**
     * Coverts a directions to radians.
     * @param dir the direction.
     * @return double
     */
    private static double directionToRad(Dir dir) {
        double d = dir.ordinal() / (Dir.values().length - 1.0);
        return d * 2 * Math.PI;
    }
    /**
     * All the possible directions of the car.
     */
    public enum Dir {
        RIGHT,
        UP,
        LEFT,
        DOWN
    }
    /**
     * Turns the car left.
     */
    public void turnLeft() {
        int dir = dir4.ordinal();
        dir -= 1;
        if(dir < 0) dir = Dir.values().length - 1;
        dir4 = Dir.values()[dir];
        setDirection(directionToRad(dir4));
    }

    /**
     * Turns the car right.
     */
    public void turnRight() {
        int dir = dir4.ordinal();
        dir += 1;
        if(dir > Dir.values().length - 1) dir = 0;
        dir4= Dir.values()[dir];
        setDirection(directionToRad(dir4));
    }
}
