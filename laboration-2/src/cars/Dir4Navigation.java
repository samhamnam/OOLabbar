package cars;

import java.awt.geom.Point2D;

/**
 * A cars.Navigation system with for directions.
 */
 public class Dir4Navigation extends Navigation {
    /**
     * Coverts a directions to radians.
     * @param dir the direction.
     * @return double
     */
    public Dir4Navigation(Point2D.Double position, double dir) {
        super(dir, position,1/2.0);
    }

    public Dir4Navigation(){
        super();
    }
}
